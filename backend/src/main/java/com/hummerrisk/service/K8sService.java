package com.hummerrisk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.hummerrisk.base.domain.*;
import com.hummerrisk.base.mapper.*;
import com.hummerrisk.base.mapper.ext.*;
import com.hummerrisk.commons.constants.*;
import com.hummerrisk.commons.exception.HRException;
import com.hummerrisk.commons.utils.*;
import com.hummerrisk.controller.request.cloudNative.*;
import com.hummerrisk.controller.request.image.ImageRequest;
import com.hummerrisk.controller.request.k8s.*;
import com.hummerrisk.controller.request.k8s.rbac.Links;
import com.hummerrisk.controller.request.k8s.rbac.Nodes;
import com.hummerrisk.dto.*;
import com.hummerrisk.i18n.Translator;
import com.hummerrisk.proxy.k8s.K8sRequest;
import com.hummerrisk.proxy.k8s.K8sSource;
import com.hummerrisk.proxy.kubesphere.KubeSphereRequest;
import com.hummerrisk.proxy.rancher.RancherRequest;
import io.kubernetes.client.openapi.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author harris
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class K8sService {

    @Resource
    private CloudNativeMapper cloudNativeMapper;
    @Resource
    private CloudNativeResultMapper cloudNativeResultMapper;
    @Resource
    private ExtCloudNativeResultMapper extCloudNativeResultMapper;
    @Resource
    private CloudNativeResultLogMapper cloudNativeResultLogMapper;
    @Resource
    private HistoryService historyService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private CloudNativeResultItemMapper cloudNativeResultItemMapper;
    @Resource
    private CloudNativeSourceMapper cloudNativeSourceMapper;
    @Resource
    private CloudNativeSourceSyncLogMapper cloudNativeSourceSyncLogMapper;
    @Resource
    private CloudNativeRuleMapper cloudNativeRuleMapper;
    @Resource
    private HistoryCloudNativeResultMapper historyCloudNativeResultMapper;
    @Resource
    private ExtCloudNativeMapper extCloudNativeMapper;
    @Resource
    private ExtCloudNativeSourceMapper extCloudNativeSourceMapper;
    @Resource
    private PluginMapper pluginMapper;
    @Resource
    private ProxyMapper proxyMapper;
    @Resource
    private CommonThreadPool commonThreadPool;
    @Resource
    private CloudNativeResultConfigItemMapper cloudNativeResultConfigItemMapper;
    @Resource
    private CloudNativeSourceImageMapper cloudNativeSourceImageMapper;
    @Resource
    private ExtK8sResultItemMapper extK8sResultItemMapper;
    @Resource
    private ExtK8sResultConfigItemMapper extK8sResultConfigItemMapper;
    @Resource
    private CloudNativeResultKubenchMapper cloudNativeResultKubenchMapper;
    @Resource
    private CloudNativeSourceRbacNodeMapper cloudNativeSourceRbacNodeMapper;
    @Resource
    private CloudNativeSourceRbacLinkMapper cloudNativeSourceRbacLinkMapper;
    @Resource
    private CloudNativeSourceRbacRelationMapper cloudNativeSourceRbacRelationMapper;
    @Resource
    private ExtCloudNativeSourceRbacMapper extCloudNativeSourceRbacMapper;


    public List<CloudNativeDTO> getCloudNativeList(CloudNativeRequest request) {
        return extCloudNativeMapper.getCloudNativeList(request);
    }

    public List<CloudNativeDTO> allCloudNativeList(CloudNativeRequest request) {
        return extCloudNativeMapper.getCloudNativeList(request);
    }

    public CloudNative getCloudNative(String id) {
        return cloudNativeMapper.selectByPrimaryKey(id);
    }

    public List<ValidateDTO> validate(List<String> ids) {
        List<ValidateDTO> list = new ArrayList<>();
        ids.forEach(id -> {
            try {
                CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
                ValidateDTO validate = validateAccount(cloudNative);
                if (validate.isFlag()) {
                    cloudNative.setStatus(CloudAccountConstants.Status.VALID.name());
                } else {
                    cloudNative.setStatus(CloudAccountConstants.Status.INVALID.name());
                    list.add(validate);
                }
                cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
            } catch (Exception e) {
                throw new HRException(Translator.get("failed_cloud_native") + e.getMessage());
            }
        });
        return list;
    }


    public ValidateDTO validate(String id) throws IOException, ApiException {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        //检验账号的有效性
        ValidateDTO valid = validateAccount(cloudNative);
        if (valid.isFlag()) {
            cloudNative.setStatus(CloudAccountConstants.Status.VALID.name());
            addCloudNativeSource(cloudNative);
        } else {
            cloudNative.setStatus(CloudAccountConstants.Status.INVALID.name());
        }
        cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
        return valid;
    }

    public ValidateDTO operatorStatusValidate(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        ValidateDTO validateDTO = validateOperatorStatus(cloudNative);
        if (validateDTO.isFlag()) {
            cloudNative.setOperatorStatus(CloudAccountConstants.Status.VALID.name());
        } else {
            cloudNative.setOperatorStatus(CloudAccountConstants.Status.INVALID.name());
        }
        cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
        return validateDTO;
    }

    public ValidateDTO kubenchStatusValidate(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        ValidateDTO validateDTO = validateKubenchStatus(cloudNative);
        if (validateDTO.isFlag()) {
            cloudNative.setKubenchStatus(CloudAccountConstants.Status.VALID.name());
        } else {
            cloudNative.setKubenchStatus(CloudAccountConstants.Status.INVALID.name());
        }
        cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
        return validateDTO;
    }

    private ValidateDTO validateOperatorStatus(CloudNative cloudNative) {
        ValidateDTO validateDTO = new ValidateDTO();
        try {
            //检验
            K8sRequest k8sRequest = new K8sRequest();
            k8sRequest.setCredential(cloudNative.getCredential());
            String token = "Bearer " + k8sRequest.getToken();
            String url = k8sRequest.getUrl();
            if (url.endsWith("/")) {
                url = url + CloudNativeConstants.URL6;
            } else {
                url = url + CloudNativeConstants.URL5;
            }
            Map<String, String> param = new HashMap<>();
            param.put("Accept", CloudNativeConstants.Accept);
            param.put("Authorization", token);
            boolean valid = HttpClientUtil.operatorStatus(url, param);
            validateDTO.setFlag(valid);
            validateDTO.setMessage("Verification : " + valid);
            return validateDTO;
        } catch (Exception e) {
            validateDTO.setFlag(false);
            validateDTO.setMessage(String.format("HRException in verifying cloud native, cloud native Operator Status: [%s], plugin: [%s], error information:%s", cloudNative.getName(), cloudNative.getPluginName(), e.getMessage()));
            LogUtil.error(String.format("HRException in verifying cloud native, cloud native Operator Status: [%s], plugin: [%s], error information:%s", cloudNative.getName(), cloudNative.getPluginName(), e.getMessage()), e);
            return validateDTO;
        }
    }

    private ValidateDTO validateKubenchStatus(CloudNative cloudNative) {
        ValidateDTO validateDTO = new ValidateDTO();
        try {
            //检验
            K8sRequest k8sRequest = new K8sRequest();
            k8sRequest.setCredential(cloudNative.getCredential());
            String token = "Bearer " + k8sRequest.getToken();
            String url = k8sRequest.getUrl();

            CloudNativeSourceExample example = new CloudNativeSourceExample();
            example.createCriteria().andCloudNativeIdEqualTo(cloudNative.getId()).andSourceTypeEqualTo("Pod").andSourceNameLike("%kube-bench-%");
            CloudNativeSource cloudNativeSource = cloudNativeSourceMapper.selectByExample(example).get(0);

            if (cloudNativeSource == null) {
                validateDTO.setFlag(false);
                validateDTO.setMessage("Verification failed!");
                return validateDTO;
            }

            if (url.endsWith("/")) {
                url = url + CloudNativeConstants.URL8 + cloudNativeSource.getSourceName();
            } else {
                url = url + CloudNativeConstants.URL7 + cloudNativeSource.getSourceName();
            }
            Map<String, String> param = new HashMap<>();
            param.put("Accept", CloudNativeConstants.Accept);
            param.put("Authorization", token);
            boolean valid = HttpClientUtil.kubenchStatus(url, param);
            validateDTO.setFlag(valid);
            validateDTO.setMessage("Verification : " + valid);
            return validateDTO;
        } catch (Exception e) {
            validateDTO.setFlag(false);
            validateDTO.setMessage(String.format("HRException in verifying cloud native, cloud native Kube-bench Status: [%s], plugin: [%s], error information:%s", cloudNative.getName(), cloudNative.getPluginName(), e.getMessage()));
            LogUtil.error(String.format("HRException in verifying cloud native, cloud native Kube-bench Status: [%s], plugin: [%s], error information:%s", cloudNative.getName(), cloudNative.getPluginName(), e.getMessage()), e);
            return validateDTO;
        }
    }

    private ValidateDTO validateAccount(CloudNative cloudNative) {
        ValidateDTO validateDTO = new ValidateDTO();
        validateDTO.setName(cloudNative.getName());
        try {
            Proxy proxy = new Proxy();
            if (cloudNative.getProxyId() != null) proxy = proxyMapper.selectByPrimaryKey(cloudNative.getProxyId());
            validateDTO.setFlag(PlatformUtils.validateCloudNative(cloudNative, proxy));
            validateDTO.setMessage("Verification succeeded!");
            return validateDTO;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            validateDTO.setFlag(false);
            validateDTO.setMessage(e.getMessage());
            return validateDTO;
        }
    }

    public ValidateDTO addCloudNative(CreateCloudNativeRequest request) {
        try {
            //参数校验
            if (StringUtils.isEmpty(request.getCredential())
                    || StringUtils.isEmpty(request.getName()) || StringUtils.isEmpty(request.getPluginId())) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_name_or_plugin"));
            }

            //云账号名称不能重复
            CloudNativeExample cloudNativeExample = new CloudNativeExample();
            cloudNativeExample.createCriteria().andNameEqualTo(request.getName());
            List<CloudNative> cloudNatives = cloudNativeMapper.selectByExampleWithBLOBs(cloudNativeExample);
            if (!CollectionUtils.isEmpty(cloudNatives)) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_name_duplicate"));
            }

            CloudNative account = new CloudNative();

            //校验云插件是否存在
            Plugin plugin = pluginMapper.selectByPrimaryKey(request.getPluginId());
            if (plugin == null) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_no_exist_plugin"));
            } else {
                BeanUtils.copyBean(account, request);
                account.setPluginIcon(Objects.requireNonNull(plugin.getIcon()));
                account.setPluginName(plugin.getName());
                account.setCreateTime(System.currentTimeMillis());
                account.setUpdateTime(System.currentTimeMillis());
                account.setCreator(Objects.requireNonNull(SessionUtils.getUser()).getId());
                account.setId(UUIDUtil.newUUID());
                //检验账号的有效性
                ValidateDTO valid = validateAccount(account);
                if (valid.isFlag()) {
                    account.setStatus(CloudAccountConstants.Status.VALID.name());
                    addCloudNativeSource(account);
                } else {
                    account.setStatus(CloudAccountConstants.Status.INVALID.name());
                }
                //检验operator
                ValidateDTO operatorStatusValidate = validateOperatorStatus(account);
                if (operatorStatusValidate.isFlag()) {
                    account.setOperatorStatus(CloudAccountConstants.Status.VALID.name());
                } else {
                    account.setOperatorStatus(CloudAccountConstants.Status.INVALID.name());
                }
                //检验kube-bench
                ValidateDTO kubenchStatusValidate = validateKubenchStatus(account);
                if (kubenchStatusValidate.isFlag()) {
                    account.setKubenchStatus(CloudAccountConstants.Status.VALID.name());
                } else {
                    account.setKubenchStatus(CloudAccountConstants.Status.INVALID.name());
                }
                cloudNativeMapper.insertSelective(account);
                reinstallOperator(account.getId());
                reinstallKubench(account.getId());
                OperationLogService.log(SessionUtils.getUser(), account.getId(), account.getName(), ResourceTypeConstants.CLOUD_NATIVE.name(), ResourceOperation.CREATE, "i18n_create_cloud_native");
                return valid;
            }
        } catch (Exception e) {
            HRException.throwException(e.getMessage());
        }
        return null;
    }

    public ValidateDTO editCloudNative(UpdateCloudNativeRequest request) throws Exception {
        try {
            //参数校验
            if (StringUtils.isEmpty(request.getCredential())
                    || StringUtils.isEmpty(request.getId())) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_id_or_plugin"));
            }

            //云账号名称不能重复
            CloudNativeExample cloudNativeExample = new CloudNativeExample();
            cloudNativeExample.createCriteria().andNameEqualTo(request.getName()).andIdNotEqualTo(request.getId());
            List<CloudNative> cloudNatives = cloudNativeMapper.selectByExampleWithBLOBs(cloudNativeExample);
            if (!CollectionUtils.isEmpty(cloudNatives)) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_name_duplicate"));
            }

            if (cloudNativeMapper.selectByPrimaryKey(request.getId()) == null) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_no_exist_id"));
            }

            CloudNative account = new CloudNative();
            //校验云插件是否存在
            Plugin plugin = pluginMapper.selectByPrimaryKey(request.getPluginId());
            if (plugin == null) {
                HRException.throwException(Translator.get("i18n_ex_cloud_native_no_exist_plugin"));
            } else {
                BeanUtils.copyBean(account, request);
                account.setPluginIcon(plugin.getIcon());
                account.setPluginName(plugin.getName());
                account.setUpdateTime(System.currentTimeMillis());
                //检验账号的有效性
                ValidateDTO valid = validateAccount(account);
                if (valid.isFlag()) {
                    account.setStatus(CloudAccountConstants.Status.VALID.name());
                    addCloudNativeSource(account);
                } else {
                    account.setStatus(CloudAccountConstants.Status.INVALID.name());
                }
                //检验operator
                ValidateDTO operatorStatusValidate = validateOperatorStatus(account);
                if (operatorStatusValidate.isFlag()) {
                    account.setOperatorStatus(CloudAccountConstants.Status.VALID.name());
                } else {
                    account.setOperatorStatus(CloudAccountConstants.Status.INVALID.name());
                }
                //检验kube-bench
                ValidateDTO kubenchStatusValidate = validateKubenchStatus(account);
                if (kubenchStatusValidate.isFlag()) {
                    account.setKubenchStatus(CloudAccountConstants.Status.VALID.name());
                } else {
                    account.setKubenchStatus(CloudAccountConstants.Status.INVALID.name());
                }
                cloudNativeMapper.updateByPrimaryKeySelective(account);
                account = cloudNativeMapper.selectByPrimaryKey(account.getId());
                //检验账号已更新状态
                OperationLogService.log(SessionUtils.getUser(), account.getId(), account.getName(), ResourceTypeConstants.CLOUD_NATIVE.name(), ResourceOperation.UPDATE, "i18n_update_cloud_native");
                return valid;
            }

        } catch (HRException | ClientException e) {
            HRException.throwException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public void delete(String accountId) {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(accountId);
        cloudNativeMapper.deleteByPrimaryKey(accountId);
        OperationLogService.log(SessionUtils.getUser(), accountId, cloudNative.getName(), ResourceTypeConstants.CLOUD_NATIVE.name(), ResourceOperation.DELETE, "i18n_delete_cloud_native");
    }

    public void addCloudNativeSource(CloudNative cloudNative) throws IOException, ApiException {
        CloudNativeSourceSyncLogWithBLOBs record = new CloudNativeSourceSyncLogWithBLOBs();
        record.setSum(0L);
        record.setOperation("i18n_sync_k8s_start");
        record.setCloudNativeId(cloudNative.getId());
        record.setCreateTime(System.currentTimeMillis());
        String creator = SessionUtils.getUser().getName();
        record.setOperator(creator);
        record.setOutput("i18n_in_process");
        record.setId(UUIDUtil.newUUID());
        cloudNativeSourceSyncLogMapper.insertSelective(record);
        commonThreadPool.addTask(() -> {
            long i = 0;
            try {
                CloudNativeSourceExample example = new CloudNativeSourceExample();
                example.createCriteria().andCloudNativeIdEqualTo(cloudNative.getId());
                List<CloudNativeSource> cloudNativeSources = cloudNativeSourceMapper.selectByExample(example);

                CloudNativeSourceRbacNodeExample cloudNativeSourceRbacNodeExample = new CloudNativeSourceRbacNodeExample();
                cloudNativeSourceRbacNodeExample.createCriteria().andK8sIdEqualTo(cloudNative.getId());
                cloudNativeSourceRbacNodeMapper.deleteByExample(cloudNativeSourceRbacNodeExample);

                CloudNativeSourceRbacLinkExample cloudNativeSourceRbacLinkExample = new CloudNativeSourceRbacLinkExample();
                cloudNativeSourceRbacLinkExample.createCriteria().andK8sIdEqualTo(cloudNative.getId());
                cloudNativeSourceRbacLinkMapper.deleteByExample(cloudNativeSourceRbacLinkExample);

                CloudNativeSourceRbacRelationExample cloudNativeSourceRbacRelationExample = new CloudNativeSourceRbacRelationExample();
                cloudNativeSourceRbacRelationExample.createCriteria().andK8sIdEqualTo(cloudNative.getId());
                cloudNativeSourceRbacRelationMapper.deleteByExample(cloudNativeSourceRbacRelationExample);

                for(CloudNativeSource ca : cloudNativeSources) {
                    CloudNativeSourceImageExample cloudNativeSourceImageExample = new CloudNativeSourceImageExample();
                    cloudNativeSourceImageExample.createCriteria().andSourceIdEqualTo(ca.getId());
                    cloudNativeSourceImageMapper.deleteByExample(cloudNativeSourceImageExample);

                    cloudNativeSourceMapper.deleteByPrimaryKey(ca.getId());
                }

                List<CloudNativeSourceWithBLOBs> list = new LinkedList<>();
                List<CloudNativeSourceImage> k8sSourceImage = new ArrayList<>();
                K8sRequest k8sRequest = new K8sRequest();
                k8sRequest.setCredential(cloudNative.getCredential());

                K8sSource version = k8sRequest.getVersion(cloudNative);
                list.addAll(version.getK8sSource());
                k8sSourceImage.addAll(version.getK8sSourceImage());

                K8sSource nameSpace = k8sRequest.getNameSpace(cloudNative);
                list.addAll(nameSpace.getK8sSource());
                k8sSourceImage.addAll(nameSpace.getK8sSourceImage());

                K8sSource node = k8sRequest.getNode(cloudNative);
                list.addAll(node.getK8sSource());
                k8sSourceImage.addAll(node.getK8sSourceImage());

                K8sSource pod = k8sRequest.getPod(cloudNative);
                list.addAll(pod.getK8sSource());
                k8sSourceImage.addAll(pod.getK8sSourceImage());

                K8sSource service = k8sRequest.getService(cloudNative);
                list.addAll(service.getK8sSource());
                k8sSourceImage.addAll(service.getK8sSourceImage());

                K8sSource serviceAccount = k8sRequest.getServiceAccount(cloudNative);
                list.addAll(serviceAccount.getK8sSource());
                k8sSourceImage.addAll(serviceAccount.getK8sSourceImage());

                K8sSource deployment = k8sRequest.getDeployment(cloudNative);
                list.addAll(deployment.getK8sSource());
                k8sSourceImage.addAll(deployment.getK8sSourceImage());

                K8sSource daemonSet = k8sRequest.getDaemonSet(cloudNative);
                list.addAll(daemonSet.getK8sSource());
                k8sSourceImage.addAll(daemonSet.getK8sSourceImage());

                K8sSource ingress = k8sRequest.getIngress(cloudNative);
                list.addAll(ingress.getK8sSource());
                k8sSourceImage.addAll(ingress.getK8sSourceImage());

                K8sSource role = k8sRequest.getRole(cloudNative);
                list.addAll(role.getK8sSource());
                k8sSourceImage.addAll(role.getK8sSourceImage());

                K8sSource roleBinding = k8sRequest.getRoleBinding(cloudNative);
                list.addAll(roleBinding.getK8sSource());
                k8sSourceImage.addAll(roleBinding.getK8sSourceImage());

                K8sSource clusterRole = k8sRequest.getClusterRole(cloudNative);
                list.addAll(clusterRole.getK8sSource());
                k8sSourceImage.addAll(clusterRole.getK8sSourceImage());

                K8sSource clusterRoleBinding = k8sRequest.getClusterRoleBinding(cloudNative);
                list.addAll(clusterRoleBinding.getK8sSource());
                k8sSourceImage.addAll(clusterRoleBinding.getK8sSourceImage());

                K8sSource secret = k8sRequest.getSecret(cloudNative);
                list.addAll(secret.getK8sSource());
                k8sSourceImage.addAll(secret.getK8sSourceImage());

                K8sSource configMap = k8sRequest.getConfigMap(cloudNative);
                list.addAll(configMap.getK8sSource());
                k8sSourceImage.addAll(configMap.getK8sSourceImage());

                K8sSource statefulSet = k8sRequest.getStatefulSet(cloudNative);
                list.addAll(statefulSet.getK8sSource());
                k8sSourceImage.addAll(statefulSet.getK8sSourceImage());

                K8sSource cronJob = k8sRequest.getCronJob(cloudNative);
                list.addAll(cronJob.getK8sSource());
                k8sSourceImage.addAll(cronJob.getK8sSourceImage());

                K8sSource job = k8sRequest.getJob(cloudNative);
                list.addAll(job.getK8sSource());
                k8sSourceImage.addAll(job.getK8sSourceImage());

                K8sSource pv = k8sRequest.getPv(cloudNative);
                list.addAll(pv.getK8sSource());
                k8sSourceImage.addAll(pv.getK8sSourceImage());

                K8sSource pvc = k8sRequest.getPvc(cloudNative);
                list.addAll(pvc.getK8sSource());
                k8sSourceImage.addAll(pvc.getK8sSourceImage());

                K8sSource lease = k8sRequest.getLease(cloudNative);
                list.addAll(lease.getK8sSource());
                k8sSourceImage.addAll(lease.getK8sSourceImage());

                K8sSource endpointSlice = k8sRequest.getEndpointSlice(cloudNative);
                list.addAll(endpointSlice.getK8sSource());
                k8sSourceImage.addAll(endpointSlice.getK8sSourceImage());

                K8sSource event = k8sRequest.getEvent(cloudNative);
                list.addAll(event.getK8sSource());
                k8sSourceImage.addAll(event.getK8sSourceImage());

                K8sSource networkPolicy = k8sRequest.getNetworkPolicy(cloudNative);
                list.addAll(networkPolicy.getK8sSource());
                k8sSourceImage.addAll(networkPolicy.getK8sSourceImage());

                for (CloudNativeSourceImage cloudNativeSourceImage : k8sSourceImage) {
                    cloudNativeSourceImageMapper.insertSelective(cloudNativeSourceImage);
                }

                for (CloudNativeSourceWithBLOBs cloudNativeSource : list) {
                    cloudNativeSource.setCreator(creator);
                    cloudNativeSourceMapper.insertSelective(cloudNativeSource);
                    i++;
                }
                record.setOutput("i18n_sync_k8s_success");
                record.setResult(true);
            } catch (IOException e) {
                LogUtil.error(e.getMessage());
                record.setOutput("i18n_sync_k8s_error:{IOException " + i + "}" + e.getMessage());
                record.setResult(false);
            } catch (ApiException e) {
                LogUtil.error(e.getMessage());
                record.setOutput("i18n_sync_k8s_error:{ApiException " + i + "}" + e.getMessage());
                record.setResult(false);
            } catch (Exception e) {
                LogUtil.error(e.getMessage());
                record.setOutput("i18n_sync_k8s_error:{Exception " + i + "}" + e.getMessage());
                record.setResult(false);
            }
            record.setSum(i);
            cloudNativeSourceSyncLogMapper.updateByPrimaryKeySelective(record);


            //Rbac 拓扑图数据处理 start
            try {
                saveK8s(cloudNative);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            CloudNativeSourceExample cloudNativeSourceExample1 = new CloudNativeSourceExample();
            cloudNativeSourceExample1.createCriteria().andCloudNativeIdEqualTo(cloudNative.getId()).andSourceTypeEqualTo(CloudNativeConstants.K8S_TYPE.RoleBinding.name());
            List<CloudNativeSourceWithBLOBs> roleBindings = cloudNativeSourceMapper.selectByExampleWithBLOBs(cloudNativeSourceExample1);
            roleBindings.stream().forEach(b -> {try {saveRoleBinding(b);} catch (Exception e) {LogUtil.error(e.getMessage());}});
            //Rbac 拓扑图数据处理 end
        });
    }

    public List<CloudNativeSourceDTO> getCloudNativeSourceList(CloudNativeSourceRequest request) {
        return extCloudNativeSourceMapper.getCloudNativeSourceList(request);
    }

    public SituationDTO situationInfo(Map<String, Object> params) {
        return extCloudNativeSourceMapper.situationInfo(params);
    }

    public void scan(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        Integer scanId = historyService.insertScanHistory(cloudNative);
        if (StringUtils.equalsIgnoreCase(cloudNative.getStatus(), CloudAccountConstants.Status.VALID.name())) {
            List<CloudNativeRule> ruleList = cloudNativeRuleMapper.selectByExample(null);
            CloudNativeResultWithBLOBs result = new CloudNativeResultWithBLOBs();

            deleteResultByCloudNativeId(id);
            for (CloudNativeRule rule : ruleList) {
                BeanUtils.copyBean(result, cloudNative);
                result.setId(UUIDUtil.newUUID());
                result.setCloudNativeId(id);
                result.setApplyUser(SessionUtils.getUserId());
                result.setCreateTime(System.currentTimeMillis());
                result.setUpdateTime(System.currentTimeMillis());
                result.setResultStatus(CloudTaskConstants.TASK_STATUS.APPROVED.toString());
                result.setUserName(SessionUtils.getUser().getName());
                result.setRuleId(rule.getId());
                result.setRuleName(rule.getName());
                result.setRuleDesc(rule.getDescription());
                result.setSeverity(rule.getSeverity());
                cloudNativeResultMapper.insertSelective(result);

                saveCloudNativeResultLog(result.getId(), "i18n_start_k8s_result", "", true);
                OperationLogService.log(SessionUtils.getUser(), result.getId(), result.getName(), ResourceTypeConstants.CLOUD_NATIVE.name(), ResourceOperation.CREATE, "i18n_start_k8s_result");

                historyService.insertScanTaskHistory(result, scanId, cloudNative.getId(), TaskEnum.k8sAccount.getType());

                historyService.insertHistoryCloudNativeResult(BeanUtils.copyBean(new HistoryCloudNativeResultWithBLOBs(), result));
            }
        }
    }

    public String reScan(String id) throws Exception {
        CloudNativeResultWithBLOBs result = cloudNativeResultMapper.selectByPrimaryKey(id);

        result.setUpdateTime(System.currentTimeMillis());
        result.setResultStatus(CloudTaskConstants.TASK_STATUS.APPROVED.toString());
        result.setUserName(SessionUtils.getUser().getName());
        cloudNativeResultMapper.updateByPrimaryKeySelective(result);

        saveCloudNativeResultLog(result.getId(), "i18n_restart_k8s_result", "", true);

        OperationLogService.log(SessionUtils.getUser(), result.getId(), result.getName(), ResourceTypeConstants.CLOUD_NATIVE.name(), ResourceOperation.CREATE, "i18n_restart_k8s_result");

        historyService.updateHistoryCloudNativeResult(BeanUtils.copyBean(new HistoryCloudNativeResultWithBLOBs(), result));

        return result.getId();
    }

    public void createScan(CloudNativeResultWithBLOBs result) throws Exception {
        try {
            CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(result.getCloudNativeId());
            if (StringUtils.equalsIgnoreCase(PlatformUtils.k8s, cloudNative.getPluginId())) {
                K8sRequest k8sRequest = new K8sRequest();
                k8sRequest.setCredential(cloudNative.getCredential());
                String token = "Bearer " + k8sRequest.getToken();
                String url = k8sRequest.getUrl();
                if (url.endsWith("/")) {
                    url = url + CloudNativeConstants.URL2;
                } else {
                    url = url + CloudNativeConstants.URL1;
                }
                Map<String, String> param = new HashMap<>();
                param.put("Accept", CloudNativeConstants.Accept);
                param.put("Authorization", token);
                String reponse1 = HttpClientUtil.HttpGet(url, param);
                result.setConfigAuditReport(reponse1);
                String url2 = k8sRequest.getUrl();
                if (url2.endsWith("/")) {
                    url2 = url2 + CloudNativeConstants.URL4;
                } else {
                    url2 = url2 + CloudNativeConstants.URL3;
                }
                String reponse2 = HttpClientUtil.HttpGet(url2, param);
                result.setVulnerabilityReport(reponse2);
            } else if (StringUtils.equalsIgnoreCase(PlatformUtils.rancher, cloudNative.getPluginId())) {
                RancherRequest rancherRequest = new RancherRequest();
                rancherRequest.setCredential(cloudNative.getCredential());
                String token = "Bearer " + rancherRequest.getToken();
                String url = rancherRequest.getUrl();
                if (url.endsWith("/")) {
                    url = url + CloudNativeConstants.URL2;
                } else {
                    url = url + CloudNativeConstants.URL1;
                }
                Map<String, String> param = new HashMap<>();
                param.put("Accept", CloudNativeConstants.Accept);
                param.put("Authorization", token);
                String reponse1 = HttpClientUtil.HttpGet(url, param);
                result.setConfigAuditReport(reponse1);
                String url2 = rancherRequest.getUrl();
                if (url2.endsWith("/")) {
                    url2 = url2 + CloudNativeConstants.URL4;
                } else {
                    url2 = url2 + CloudNativeConstants.URL3;
                }
                String reponse2 = HttpClientUtil.HttpGet(url2, param);
                result.setVulnerabilityReport(reponse2);
            } else if (StringUtils.equalsIgnoreCase(PlatformUtils.openshift, cloudNative.getPluginId())) {

            } else if (StringUtils.equalsIgnoreCase(PlatformUtils.kubesphere, cloudNative.getPluginId())) {
                KubeSphereRequest kubeSphereRequest = new KubeSphereRequest();
                kubeSphereRequest.setCredential(cloudNative.getCredential());
                String token = "Bearer " + kubeSphereRequest.getToken();
                String url = kubeSphereRequest.getUrl();
                if (url.endsWith("/")) {
                    url = url + CloudNativeConstants.URL2;
                } else {
                    url = url + CloudNativeConstants.URL1;
                }
                Map<String, String> param = new HashMap<>();
                param.put("Accept", CloudNativeConstants.Accept);
                param.put("Authorization", token);
                String reponse1 = HttpClientUtil.HttpGet(url, param);
                result.setConfigAuditReport(reponse1);
                String url2 = kubeSphereRequest.getUrl();
                if (url2.endsWith("/")) {
                    url2 = url2 + CloudNativeConstants.URL4;
                } else {
                    url2 = url2 + CloudNativeConstants.URL3;
                }
                String reponse2 = HttpClientUtil.HttpGet(url2, param);
                result.setVulnerabilityReport(reponse2);
            }
            result.setUpdateTime(System.currentTimeMillis());
            result.setResultStatus(CloudTaskConstants.TASK_STATUS.FINISHED.toString());

            long count = saveResultItem(result);
            result.setReturnSum(count);
            long sum = saveResultConfigItem(result);
            result.setReturnConfigSum(sum);
            result.setKubeBench(scanKubeBench(cloudNative, result.getId()));
            cloudNativeResultMapper.updateByPrimaryKeySelective(result);

            noticeService.createCloudNativeMessageOrder(result);
            saveCloudNativeResultLog(result.getId(), "i18n_end_k8s_result", "", true);

            historyService.updateHistoryCloudNativeResult(BeanUtils.copyBean(new HistoryCloudNativeResultWithBLOBs(), result));
        } catch (Exception e) {
            LogUtil.error("create K8sResult: " + e.getMessage());
            result.setUpdateTime(System.currentTimeMillis());
            result.setResultStatus(CloudTaskConstants.TASK_STATUS.ERROR.toString());
            cloudNativeResultMapper.updateByPrimaryKeySelective(result);
            historyService.updateHistoryCloudNativeResult(BeanUtils.copyBean(new HistoryCloudNativeResultWithBLOBs(), result));
            saveCloudNativeResultLog(result.getId(), "i18n_operation_ex", e.getMessage(), false);
        }
    }

    public String scanKubeBench(CloudNative cloudNative, String resultId) throws Exception {
        try {
            K8sRequest k8sRequest = new K8sRequest();
            k8sRequest.setCredential(cloudNative.getCredential());
            String token = "Bearer " + k8sRequest.getToken();
            String url = k8sRequest.getUrl();

            CloudNativeSourceExample example = new CloudNativeSourceExample();
            example.createCriteria().andCloudNativeIdEqualTo(cloudNative.getId()).andSourceTypeEqualTo("Pod").andSourceNameLike("%kube-bench-%");

            ValidateDTO kubenchIsInstall = validateKubenchStatus(cloudNative);
            if (!kubenchIsInstall.isFlag()) {
                createKubench(k8sRequest, example, cloudNative);
            }

            CloudNativeSource cloudNativeSource = cloudNativeSourceMapper.selectByExample(example).get(0);

            if (cloudNativeSource == null) {
                return "";
            }

            if (url.endsWith("/")) {
                url = url + CloudNativeConstants.URL8 + cloudNativeSource.getSourceName() + "/log";
            } else {
                url = url + CloudNativeConstants.URL7 + cloudNativeSource.getSourceName() + "/log";
            }
            Map<String, String> param = new HashMap<>();
            param.put("Accept", CloudNativeConstants.Accept);
            param.put("Authorization", token);
            String reponse = HttpClientUtil.HttpGet(url, param);
            saveKubenchResultItem(reponse, resultId);
            return reponse;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        return "";
    }

    void createKubench(K8sRequest k8sRequest, CloudNativeSourceExample example, CloudNative cloudNative) throws Exception {
        try {
            k8sRequest.deleteKubenchJob();
            List<CloudNativeSource> list = cloudNativeSourceMapper.selectByExample(example);
            cloudNativeSourceMapper.deleteByExample(example);
            for (CloudNativeSource cloudNativeSource : list) {
                k8sRequest.deleteKubenchPod(cloudNativeSource.getSourceName());
            }
            k8sRequest.createKubenchJob();
            K8sSource pod = k8sRequest.getKubenchPod(cloudNative);
            for (CloudNativeSourceWithBLOBs k8sSource : pod.getK8sSource()) {
                cloudNativeSourceMapper.insertSelective(k8sSource);
            }
            for (CloudNativeSourceImage cloudNativeSourceImage : pod.getK8sSourceImage()) {
                cloudNativeSourceImageMapper.insertSelective(cloudNativeSourceImage);
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
    }

    void saveKubenchResultItem(String reponse, String resultId) throws Exception {
        try {
            String[] results = reponse.split("\n\n\n");
            for (String result : results) {
                if (StringUtils.isEmpty(result)) continue;
                String[] strs = null;
                if (result.contains("== Remediations master ==")) {
                    strs = result.split("== Remediations master ==");
                } else if (result.contains("== Remediations etcd ==")) {
                    strs = result.split("== Remediations etcd ==");
                } else if (result.contains("== Remediations controlplane ==")) {
                    strs = result.split("== Remediations controlplane ==");
                } else if (result.contains("== Remediations node ==")) {
                    strs = result.split("== Remediations node ==");
                } else if (result.contains("== Remediations policies ==")) {
                    strs = result.split("== Remediations policies ==");
                }
                if (strs == null) continue;
                for (String str : strs) {
                    if (StringUtils.isEmpty(str) || str == null) continue;
                    if (str.contains("[PASS]") || str.contains("[INFO]") || str.contains("[WARN]") || str.contains("[FAIL]")) {
                        InputStreamReader read = new InputStreamReader(new ByteArrayInputStream(str.getBytes()));
                        BufferedReader bufferedReader = new BufferedReader(read);
                        String lineTxt;
                        while ((lineTxt = bufferedReader.readLine()) != null) {
                            if (lineTxt != null && !StringUtils.isEmpty(lineTxt) && (lineTxt.contains("[PASS]") || lineTxt.contains("[INFO]") || lineTxt.contains("[WARN]") || lineTxt.contains("[FAIL]"))) {
                                CloudNativeResultKubenchWithBLOBs kubenchWithBLOBs = new CloudNativeResultKubenchWithBLOBs();
                                kubenchWithBLOBs.setResultId(resultId);
                                kubenchWithBLOBs.setCreateTime(System.currentTimeMillis());
                                kubenchWithBLOBs.setTitle(lineTxt);
                                if (lineTxt.contains("[PASS]")) {
                                    kubenchWithBLOBs.setSeverity("PASS");
                                }
                                if (lineTxt.contains("[INFO]")) {
                                    kubenchWithBLOBs.setSeverity("INFO");
                                }
                                if (lineTxt.contains("[WARN]")) {
                                    kubenchWithBLOBs.setSeverity("WARN");
                                }
                                if (lineTxt.contains("[FAIL]")) {
                                    kubenchWithBLOBs.setSeverity("FAIL");
                                }
                                kubenchWithBLOBs.setNumber(lineTxt.split("\\s+")[1]);
                                kubenchWithBLOBs.setId(UUIDUtil.newUUID());
                                cloudNativeResultKubenchMapper.insertSelective(kubenchWithBLOBs);
                            }
                        }
                        bufferedReader.close();
                        read.close();
                    } else {
                        String[] descriptions = str.split("\n\n");
                        if (descriptions == null) continue;
                        for (String desc : descriptions) {
                            if (StringUtils.isEmpty(desc) || desc == null) continue;
                            if (desc.startsWith("\n")) desc = desc.replaceFirst("\n", "");
                            String number = desc.split("\\s+")[0];
                            CloudNativeResultKubenchExample example = new CloudNativeResultKubenchExample();
                            example.createCriteria().andResultIdEqualTo(resultId).andNumberEqualTo(number);
                            List<CloudNativeResultKubenchWithBLOBs> list = cloudNativeResultKubenchMapper.selectByExampleWithBLOBs(example);
                            if (list.size() < 1) continue;
                            CloudNativeResultKubenchWithBLOBs record = list.get(0);
                            record.setDescription(desc);
                            cloudNativeResultKubenchMapper.updateByPrimaryKeySelective(record);
                        }
                    }
                }

            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
    }

    public void deleteResultByCloudNativeId(String id) throws Exception {
        CloudNativeResultExample example = new CloudNativeResultExample();
        example.createCriteria().andCloudNativeIdEqualTo(id);
        cloudNativeResultMapper.deleteByExample(example);
    }

    void saveCloudNativeResultLog(String resultId, String operation, String output, boolean result) throws Exception {
        CloudNativeResultLogWithBLOBs cloudNativeResultLog = new CloudNativeResultLogWithBLOBs();
        String operator = "system";
        try {
            if (SessionUtils.getUser() != null) {
                operator = SessionUtils.getUser().getId();
            }
        } catch (Exception e) {
            //防止单元测试无session
        }
        cloudNativeResultLog.setOperator(operator);
        cloudNativeResultLog.setResultId(resultId);
        cloudNativeResultLog.setCreateTime(System.currentTimeMillis());
        cloudNativeResultLog.setOperation(operation);
        cloudNativeResultLog.setOutput(output);
        cloudNativeResultLog.setResult(result);
        cloudNativeResultLogMapper.insertSelective(cloudNativeResultLog);

    }

    long saveResultItem(CloudNativeResultWithBLOBs result) throws Exception {

        String json = result.getVulnerabilityReport();
        JSONObject jsonObject1 = JSON.parseObject(json);
        JSONArray jsonArray1 = jsonObject1.getJSONArray("items");
        int i = 0;
        if (jsonArray1 != null) {
            for (Object object : jsonArray1) {
                JSONObject obj1 = (JSONObject) object;
                JSONObject report = obj1.getJSONObject("report");
                JSONArray jsonArray = report.getJSONArray("vulnerabilities");
                JSONObject artifact = report.getJSONObject("artifact");
                JSONObject registry = report.getJSONObject("registry");
                String image = registry.get("server") + "/" + artifact.get("repository") + ":" + artifact.get("tag");
                for (Object object2 : jsonArray) {
                    JSONObject obj2 = (JSONObject) object2;
                    CloudNativeResultItem cloudNativeResultItem = new CloudNativeResultItem();
                    cloudNativeResultItem.setId(UUIDUtil.newUUID());
                    cloudNativeResultItem.setResultId(result.getId());
                    cloudNativeResultItem.setResource(obj2.getString("resource"));
                    cloudNativeResultItem.setPrimaryLink(obj2.getString("primaryLink"));
                    cloudNativeResultItem.setScore(obj2.getString("score"));
                    cloudNativeResultItem.setSeverity(obj2.getString("severity"));
                    cloudNativeResultItem.setTarget(obj2.getString("target"));
                    cloudNativeResultItem.setTitle(obj2.getString("title"));
                    cloudNativeResultItem.setVulnerabilityId(obj2.getString("vulnerabilityID"));
                    cloudNativeResultItem.setInstalledVersion(obj2.getString("installedVersion"));
                    cloudNativeResultItem.setFixedVersion(obj2.getString("fixedVersion"));
                    cloudNativeResultItem.setLinks(obj2.getString("links"));
                    cloudNativeResultItem.setCreateTime(System.currentTimeMillis());
                    cloudNativeResultItem.setImage(image);
                    cloudNativeResultItemMapper.insertSelective(cloudNativeResultItem);

                    i++;
                }
            }
        }
        return i;
    }

    long saveResultConfigItem(CloudNativeResultWithBLOBs result) throws Exception {

        String json = result.getConfigAuditReport();
        JSONObject jsonObject1 = JSON.parseObject(json);
        JSONArray jsonArray1 = jsonObject1.getJSONArray("items");
        int i = 0;
        if (jsonArray1 != null) {
            for (Object object : jsonArray1) {
                JSONObject obj1 = (JSONObject) object;
                JSONObject jsonObject2 = obj1.getJSONObject("report");
                JSONArray jsonArray = jsonObject2.getJSONArray("checks");
                for (Object object2 : jsonArray) {
                    JSONObject obj2 = (JSONObject) object2;
                    String success = obj2.getString("success");
                    if (success != null && StringUtils.equalsIgnoreCase(success, "false")) {
                        CloudNativeResultConfigItemWithBLOBs item = new CloudNativeResultConfigItemWithBLOBs();
                        item.setId(UUIDUtil.newUUID());
                        item.setResultId(result.getId());
                        item.setCategory(obj2.getString("category"));
                        item.setCheckId(obj2.getString("checkID"));
                        item.setSeverity(obj2.getString("severity"));
                        item.setSuccess(success);
                        item.setDescription(obj2.getString("description"));
                        item.setTitle(obj2.getString("title"));
                        item.setMessages(obj2.getString("messages"));
                        item.setCreateTime(System.currentTimeMillis());
                        cloudNativeResultConfigItemMapper.insertSelective(item);

                        i++;
                    }
                }
            }
        }
        return i;
    }

    public List<CloudNativeResultDTO> resultList(K8sResultRequest request) {
        List<CloudNativeResultDTO> list = extCloudNativeResultMapper.resultList(request);
        return list;
    }

    public List<CloudNativeResultItem> resultItemList(K8sResultRequest resourceRequest) {
        CloudNativeResultItemExample example = new CloudNativeResultItemExample();
        if (resourceRequest.getName() != null && !StringUtils.isBlank(resourceRequest.getName())) {
            example.createCriteria().andResultIdEqualTo(resourceRequest.getResultId()).andTitleLike("%" + resourceRequest.getName() + "%");
        } else {
            example.createCriteria().andResultIdEqualTo(resourceRequest.getResultId());
        }
        example.setOrderByClause("FIELD(`severity`, 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW', 'UNKNOWN')");
        return cloudNativeResultItemMapper.selectByExample(example);
    }

    public List<CloudNativeResultItem> resultItemListBySearch(K8sResultItemRequest request) {
        return extK8sResultItemMapper.resultItemListBySearch(request);
    }

    public List<CloudNativeResultConfigItemWithBLOBs> resultConfigItemList(K8sResultRequest resourceRequest) {
        CloudNativeResultConfigItemExample example = new CloudNativeResultConfigItemExample();
        if (resourceRequest.getName() != null && !StringUtils.isBlank(resourceRequest.getName())) {
            example.createCriteria().andResultIdEqualTo(resourceRequest.getResultId()).andTitleLike("%" + resourceRequest.getName() + "%");
        } else {
            example.createCriteria().andResultIdEqualTo(resourceRequest.getResultId());
        }
        example.setOrderByClause("FIELD(`severity`, 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW', 'UNKNOWN')");
        return cloudNativeResultConfigItemMapper.selectByExampleWithBLOBs(example);
    }

    public List<CloudNativeResultConfigItemWithBLOBs> resultConfigItemListBySearch(K8sConfigResultItemRequest request) {
        return extK8sResultConfigItemMapper.resultConfigItemListBySearch(request);
    }

    public List<CloudNativeResultKubenchWithBLOBs> resultKubenchItemListBySearch(K8sKubenchResultItemRequest request) {
        return extK8sResultConfigItemMapper.resultKubenchItemListBySearch(request);
    }

    public CloudNativeResultDTO getCloudNativeResult(String resultId) {
        CloudNativeResultDTO cloudNativeResult = extCloudNativeResultMapper.getCloudNativeResult(resultId);
        return cloudNativeResult;
    }

    public List<ImageDTO> imageList(ImageRequest request) {
        return extCloudNativeResultMapper.imageList(request);
    }

    public void deleteCloudNativeResult(String id) throws Exception {
        cloudNativeResultMapper.deleteByPrimaryKey(id);
    }

    public List<CloudNativeResultLogWithBLOBs> getCloudNativeResultLog(String resultId) {
        CloudNativeResultLogExample example = new CloudNativeResultLogExample();
        example.createCriteria().andResultIdEqualTo(resultId);
        return cloudNativeResultLogMapper.selectByExampleWithBLOBs(example);
    }

    public List<CloudNativeResultLogWithBLOBs> topoLog(String accountId) {
        CloudNativeResultExample cloudNativeResultExample = new CloudNativeResultExample();
        cloudNativeResultExample.createCriteria().andCloudNativeIdEqualTo(accountId);
        CloudNativeResult cloudNativeResult = cloudNativeResultMapper.selectByExample(cloudNativeResultExample).get(0);
        CloudNativeResultLogExample example = new CloudNativeResultLogExample();
        example.createCriteria().andResultIdEqualTo(cloudNativeResult.getId());
        return cloudNativeResultLogMapper.selectByExampleWithBLOBs(example);
    }

    public CloudNativeResultWithBLOBs topoResult(String accountId) {
        CloudNativeResultExample cloudNativeResultExample = new CloudNativeResultExample();
        cloudNativeResultExample.createCriteria().andCloudNativeIdEqualTo(accountId);
        CloudNativeResult cloudNativeResult = cloudNativeResultMapper.selectByExample(cloudNativeResultExample).get(0);
        CloudNativeResultWithBLOBs cloudNativeResultWithBLOBs = cloudNativeResultMapper.selectByPrimaryKey(cloudNativeResult.getId());
        return cloudNativeResultWithBLOBs;
    }

    public CloudNativeResultWithBLOBs getCloudNativeResultWithBLOBs(String resultId) {
        CloudNativeResultWithBLOBs cloudNativeResultWithBLOBs = cloudNativeResultMapper.selectByPrimaryKey(resultId);
        return cloudNativeResultWithBLOBs;
    }

    public List<CloudNativeSourceWithBLOBs> allCloudNativeSource2YamlList() {
        CloudNativeSourceExample example = new CloudNativeSourceExample();
        example.createCriteria().andSourceTypeNotEqualTo("Version").andSourceYamlIsNotNull();
        return cloudNativeSourceMapper.selectByExampleWithBLOBs(example);
    }

    public List<CloudNativeSourceSyncLogWithBLOBsDTO> syncList(CloudNativeSyncLogRequest request) {
        return extCloudNativeResultMapper.syncList(request);
    }

    public void syncSource(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        addCloudNativeSource(cloudNative);
    }

    public void deleteSyncLog(String id) throws Exception {
        cloudNativeSourceSyncLogMapper.deleteByPrimaryKey(id);
    }

    public MetricChartDTO metricChart(String resultId) {
        return extCloudNativeResultMapper.metricChart(resultId);
    }

    public MetricChartDTO metricConfigChart(String resultId) {
        return extCloudNativeResultMapper.metricConfigChart(resultId);
    }

    public KubenchChartDTO kubenchChart(String resultId) {
        return extCloudNativeResultMapper.kubenchChart(resultId);
    }

    public String download(Map<String, Object> map) {
        HistoryCloudNativeResultWithBLOBs historyCloudNativeResultWithBLOBs = historyCloudNativeResultMapper.selectByPrimaryKey(map.get("id").toString());
        JSONObject str = JSON.parseObject(historyCloudNativeResultWithBLOBs.getVulnerabilityReport() != null ? historyCloudNativeResultWithBLOBs.getVulnerabilityReport() : "{}");
        JSONObject str2 = JSON.parseObject(historyCloudNativeResultWithBLOBs.getConfigAuditReport() != null ? historyCloudNativeResultWithBLOBs.getConfigAuditReport() : "{}");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(str);
        jsonArray.add(str2);
        return jsonArray.toJSONString();
    }

    public Map<String, Object> topInfo(Map<String, Object> params) {
        return extCloudNativeMapper.topInfo(params);
    }

    public List<Map<String, Object>> k8sChart() {
        return extCloudNativeMapper.k8sChart();
    }

    public List<Map<String, Object>> severityChart() {
        return extCloudNativeMapper.severityChart();
    }

    public List<CloudNative> allList() {
        return cloudNativeMapper.selectByExample(null);
    }

    public List<HistoryCloudNativeResultDTO> history(Map<String, Object> params) {
        List<HistoryCloudNativeResultDTO> historyList = extCloudNativeResultMapper.history(params);
        return historyList;
    }

    public List<CloudNativeResultItem> historyResultItemList(CloudNativeResultItem item) {
        CloudNativeResultItemExample example = new CloudNativeResultItemExample();
        example.createCriteria().andResultIdEqualTo(item.getResultId());
        example.setOrderByClause("FIELD(`severity`, 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW', 'UNKNOWN')");
        return cloudNativeResultItemMapper.selectByExampleWithBLOBs(example);
    }

    public List<CloudNativeResultConfigItemWithBLOBs> historyResultConfigItemList(CloudNativeResultConfigItem item) {
        CloudNativeResultConfigItemExample example = new CloudNativeResultConfigItemExample();
        example.createCriteria().andResultIdEqualTo(item.getResultId());
        example.setOrderByClause("FIELD(`severity`, 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW', 'UNKNOWN')");
        return cloudNativeResultConfigItemMapper.selectByExampleWithBLOBs(example);
    }

    public List<CloudNativeResultKubenchWithBLOBs> historyResultKubenchList(CloudNativeResultKubenchWithBLOBs item) {
        CloudNativeResultKubenchExample example = new CloudNativeResultKubenchExample();
        example.createCriteria().andResultIdEqualTo(item.getResultId());
        example.setOrderByClause("FIELD(`severity`, 'FAIL', 'WARN', 'INFO', 'PASS')");
        return cloudNativeResultKubenchMapper.selectByExampleWithBLOBs(example);
    }

    public void deleteHistoryK8sResult(String id) throws Exception {
        historyCloudNativeResultMapper.deleteByPrimaryKey(id);
    }

    public K8sTopology k8sTopology() {
        return extCloudNativeSourceMapper.k8sTopology();
    }

    public RiskTopology riskTopology(RiskRequest request) {
        return extCloudNativeSourceMapper.riskTopology(request);
    }

    public K8sImage getImage(RiskRequest request) {
        return extCloudNativeSourceMapper.getImage(request);
    }

    public NodeTopology nodeTopology() {
        return extCloudNativeSourceMapper.nodeTopology();
    }

    public NameSpaceTopology namespaceTopology() {
        return extCloudNativeSourceMapper.namespaceTopology();
    }

    public List<CloudNativeSourceImageDTO> sourceImages(String sourceId) throws Exception {
        List<CloudNativeSourceImageDTO> sourceImages = new ArrayList<>();
        CloudNativeSourceImageExample example = new CloudNativeSourceImageExample();
        example.createCriteria().andSourceIdEqualTo(sourceId);
        List<CloudNativeSourceImage> images = cloudNativeSourceImageMapper.selectByExample(example);
        for (CloudNativeSourceImage image : images) {
            CloudNativeSourceImageDTO dto = BeanUtils.copyBean(new CloudNativeSourceImageDTO(), image);
            String imageName = image.getImage();
            CloudNativeSource cloudNativeSource = cloudNativeSourceMapper.selectByPrimaryKey(image.getSourceId());
            CloudNativeResultExample cloudNativeResultExample = new CloudNativeResultExample();
            cloudNativeResultExample.createCriteria().andCloudNativeIdEqualTo(cloudNativeSource.getCloudNativeId());
            CloudNativeResult cloudNativeResult = cloudNativeResultMapper.selectByExample(cloudNativeResultExample).get(0);
            CloudNativeResultItemExample cloudNativeResultItemExample = new CloudNativeResultItemExample();
            cloudNativeResultItemExample.createCriteria().andResultIdEqualTo(cloudNativeResult.getId()).andImageEqualTo(imageName);
            List<CloudNativeResultItem> list = cloudNativeResultItemMapper.selectByExample(cloudNativeResultItemExample);
            if (list.size() > 0) {
                dto.setRisk("yes");
            } else {
                dto.setRisk("no");
            }
            sourceImages.add(dto);
        }
        return sourceImages;
    }

    public void reinstallOperator(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        try {
            saveCloudNativeResultLog(id, "i18n_start_k8s_operator", "", true);

            ValidateDTO operatorIsInstall = validateOperatorStatus(cloudNative);
            if (operatorIsInstall.isFlag()) {
                saveCloudNativeResultLog(id, "i18n_already_k8s_operator", "", true);
                return;
            }

            K8sRequest k8sRequest = new K8sRequest();
            k8sRequest.setCredential(cloudNative.getCredential());

            k8sRequest.deleteChartRepo();
            k8sRequest.createChartRepo();
            k8sRequest.deleteOperatorChart();
            k8sRequest.createOperatorChart();

            //检验operator
            ValidateDTO operatorStatusValidate = validateOperatorStatus(cloudNative);
            if (operatorStatusValidate.isFlag()) {
                cloudNative.setOperatorStatus(CloudAccountConstants.Status.VALID.name());
            } else {
                cloudNative.setOperatorStatus(CloudAccountConstants.Status.INVALID.name());
            }
            cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);

            saveCloudNativeResultLog(id, "i18n_end_k8s_operator", "", true);
        } catch (Exception e) {
            cloudNative.setOperatorStatus(CloudAccountConstants.Status.INVALID.name());
            cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
            saveCloudNativeResultLog(id, "i18n_operation_ex", e.getMessage(), false);
        }

        //检验operator
        operatorStatusValidate(cloudNative.getId());
    }

    public void reinstallKubench(String id) throws Exception {
        CloudNative cloudNative = cloudNativeMapper.selectByPrimaryKey(id);
        try {
            saveCloudNativeResultLog(id, "i18n_start_k8s_kubench", "", true);

            ValidateDTO kubenchIsInstall = validateKubenchStatus(cloudNative);
            if (kubenchIsInstall.isFlag()) {
                saveCloudNativeResultLog(id, "i18n_already_k8s_kubench", "", true);
                return;
            }

            K8sRequest k8sRequest = new K8sRequest();
            k8sRequest.setCredential(cloudNative.getCredential());

            CloudNativeSourceExample example = new CloudNativeSourceExample();
            example.createCriteria().andCloudNativeIdEqualTo(cloudNative.getId()).andSourceTypeEqualTo("Pod").andSourceNameLike("%kube-bench-%");

            createKubench(k8sRequest, example, cloudNative);

            saveCloudNativeResultLog(id, "i18n_end_k8s_kubench", "", true);
        } catch (Exception e) {
            cloudNative.setKubenchStatus(CloudAccountConstants.Status.INVALID.name());
            cloudNativeMapper.updateByPrimaryKeySelective(cloudNative);
            saveCloudNativeResultLog(id, "i18n_operation_ex" + ": " + e.getMessage(), e.getMessage(), false);
        }

        //检验kube-bench
        kubenchStatusValidate(cloudNative.getId());
    }

    public RbacDTO rbacChart(String k8sId) throws Exception {
        try {
            RbacDTO rbacDTO = new RbacDTO();
            List<Nodes> nodes = extCloudNativeSourceRbacMapper.nodes(k8sId);
            List<Links> links = extCloudNativeSourceRbacMapper.links(k8sId);
            rbacDTO.setNodes(nodes);
            rbacDTO.setLinks(links);
            return rbacDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveK8s(CloudNative cloudNative) throws Exception {
        try {
            CloudNativeSourceRbacNode k8s = new CloudNativeSourceRbacNode();
            k8s.setId(UUIDUtil.newUUID());
            k8s.setName(cloudNative.getName());
            k8s.setK8sId(cloudNative.getId());
            k8s.setValue(1);
            k8s.setSymbolsize(70);
            k8s.setSymbol("@/assets/img/platform/k8s.png");
            k8s.setCategory(CloudNativeConstants.K8S_TYPE.K8S.name());
            k8s.setCreateTime(System.currentTimeMillis());
            k8s.setOrder(0L);
            cloudNativeSourceRbacNodeMapper.insertSelective(k8s);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveRole(CloudNativeSourceWithBLOBs cloudNativeSource) throws Exception {
        try {
            String json = cloudNativeSource.getSourceJson();
            if (!StringUtils.isEmpty(json)) {
                JSONObject sourceJson = JSONObject.parseObject(json);
                CloudNativeSourceRbacNode check = new CloudNativeSourceRbacNode();
                check.setName(cloudNativeSource.getSourceName());
                check.setNamespace(cloudNativeSource.getSourceNamespace());
                check.setK8sId(cloudNativeSource.getCloudNativeId());
                check.setCategory(CloudNativeConstants.K8S_TYPE.Role.name());
                List<CloudNativeSourceRbacNode> nodes = checkNode(check);
                CloudNativeSourceRbacNode roleSource = new CloudNativeSourceRbacNode();
                if (nodes.size() == 0) {
                    roleSource.setId(UUIDUtil.newUUID());
                    roleSource.setName(cloudNativeSource.getSourceName());
                    roleSource.setNamespace(cloudNativeSource.getSourceNamespace());
                    roleSource.setK8sId(cloudNativeSource.getCloudNativeId());
                    roleSource.setValue(1);
                    roleSource.setSymbolsize(35);
                    roleSource.setCategory(CloudNativeConstants.K8S_TYPE.Role.name());
                    roleSource.setCreateTime(System.currentTimeMillis());
                    roleSource.setOrder(caluOrder(roleSource));
                    cloudNativeSourceRbacNodeMapper.insertSelective(roleSource);

                    CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                    String linkId = UUIDUtil.newUUID();
                    link.setId(linkId);
                    link.setK8sId(cloudNativeSource.getCloudNativeId());
                    link.setSource("0");
                    link.setTarget(roleSource.getOrder().toString());
                    link.setCreateTime(System.currentTimeMillis());
                    cloudNativeSourceRbacLinkMapper.insertSelective(link);

                    CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                    relation.setId(UUIDUtil.newUUID());
                    relation.setK8sId(cloudNativeSource.getCloudNativeId());
                    relation.setLinkId(linkId);
                    relation.setName("Manage");
                    relation.setCreateTime(System.currentTimeMillis());
                    cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                } else {
                    roleSource = nodes.get(0);
                }

                String rules = sourceJson.getString("rules");
                JSONArray jsonArray = JSONArray.parseArray(rules);
                for (Object object : jsonArray) {
                    JSONObject subjectJson = (JSONObject) object;
                    String resourceNames = subjectJson.getString("resourceNames");
                    String resources = subjectJson.getString("resources");

                    if (StringUtils.isEmpty(resourceNames)) {
                        JSONArray resourcesArray = JSONArray.parseArray(resources);
                        for (Object obj : resourcesArray) {
                            String resource = (String) obj;
                            CloudNativeSourceRbacNode resourceSource = new CloudNativeSourceRbacNode();
                            resourceSource.setId(UUIDUtil.newUUID());
                            resourceSource.setName(resource);
                            resourceSource.setNamespace(cloudNativeSource.getSourceNamespace());
                            resourceSource.setK8sId(cloudNativeSource.getCloudNativeId());
                            resourceSource.setValue(1);
                            resourceSource.setSymbolsize(25);
                            resourceSource.setCategory(CloudNativeConstants.K8S_TYPE.ResourceType.name());
                            resourceSource.setCreateTime(System.currentTimeMillis());

                            List<CloudNativeSourceRbacNode> csrList = checkNode(resourceSource);
                            if (csrList.size() > 0) {
                                resourceSource = csrList.get(0);
                            } else {
                                resourceSource.setOrder(caluOrder(resourceSource));
                                cloudNativeSourceRbacNodeMapper.insertSelective(resourceSource);
                            }

                            CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                            String linkId = UUIDUtil.newUUID();
                            link.setId(linkId);
                            link.setK8sId(cloudNativeSource.getCloudNativeId());
                            link.setSource(roleSource.getOrder().toString());
                            link.setTarget(resourceSource.getOrder().toString());
                            link.setCreateTime(System.currentTimeMillis());
                            List<CloudNativeSourceRbacLink> cloudNativeSourceRbacLinks = checkLink(link);
                            if(cloudNativeSourceRbacLinks.size() == 0) {
                                cloudNativeSourceRbacLinkMapper.insertSelective(link);
                            }

                            String verbs = subjectJson.getString("verbs");
                            JSONArray verbsArray = JSONArray.parseArray(verbs);
                            String relations = "";
                            int i = 0;
                            for (Object obj2 : verbsArray) {
                                String rela = (String) obj2;
                                if(i == 0) {
                                    relations = rela;
                                } else {
                                    relations = relations + "/" + rela;
                                }
                                i++;
                            }

                            CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                            relation.setId(UUIDUtil.newUUID());
                            relation.setK8sId(cloudNativeSource.getCloudNativeId());
                            relation.setLinkId(linkId);
                            relation.setName(relations);
                            relation.setCreateTime(System.currentTimeMillis());
                            List<CloudNativeSourceRbacRelation> cloudNativeSourceRbacRelations = checkRela(relation);
                            if(cloudNativeSourceRbacRelations.size() == 0) {
                                cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                            }
                        }
                    } else {
                        JSONArray resourceNamesArray = JSONArray.parseArray(resourceNames);
                        for (Object obj : resourceNamesArray) {
                            String resource = (String) obj;
                            CloudNativeSourceRbacNode resourceSource = new CloudNativeSourceRbacNode();
                            resourceSource.setId(UUIDUtil.newUUID());
                            resourceSource.setName(resource);
                            resourceSource.setNamespace(cloudNativeSource.getSourceNamespace());
                            resourceSource.setK8sId(cloudNativeSource.getCloudNativeId());
                            resourceSource.setValue(1);
                            resourceSource.setSymbolsize(25);
                            resourceSource.setCategory(CloudNativeConstants.K8S_TYPE.Resource.name());
                            resourceSource.setCreateTime(System.currentTimeMillis());

                            CloudNativeSourceRbacNodeExample csr = new CloudNativeSourceRbacNodeExample();
                            csr.createCriteria().andNameEqualTo(resourceSource.getName()).andNamespaceEqualTo(resourceSource.getNamespace()).andK8sIdEqualTo(cloudNativeSource.getCloudNativeId()).andCategoryEqualTo(resourceSource.getCategory());
                            List<CloudNativeSourceRbacNode> csrList = cloudNativeSourceRbacNodeMapper.selectByExample(csr);
                            if (csrList.size() > 0) {
                                resourceSource = csrList.get(0);
                            } else {
                                resourceSource.setOrder(caluOrder(resourceSource));
                                cloudNativeSourceRbacNodeMapper.insertSelective(resourceSource);
                            }

                            CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                            String linkId = UUIDUtil.newUUID();
                            link.setId(linkId);
                            link.setK8sId(cloudNativeSource.getCloudNativeId());
                            link.setSource(roleSource.getOrder().toString());
                            link.setTarget(resourceSource.getOrder().toString());
                            link.setCreateTime(System.currentTimeMillis());
                            List<CloudNativeSourceRbacLink> cloudNativeSourceRbacLinks = checkLink(link);
                            if(cloudNativeSourceRbacLinks.size() == 0) {
                                cloudNativeSourceRbacLinkMapper.insertSelective(link);
                            }

                            String verbs = subjectJson.getString("verbs");
                            JSONArray verbsArray = JSONArray.parseArray(verbs);
                            String relations = "";
                            int i = 0;
                            for (Object obj2 : verbsArray) {
                                String rela = (String) obj2;
                                if(i == 0) {
                                    relations = rela;
                                } else {
                                    relations = relations + "/" + rela;
                                }
                                i++;
                            }

                            CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                            relation.setId(UUIDUtil.newUUID());
                            relation.setK8sId(cloudNativeSource.getCloudNativeId());
                            relation.setLinkId(linkId);
                            relation.setName(relations);
                            relation.setCreateTime(System.currentTimeMillis());
                            List<CloudNativeSourceRbacRelation> cloudNativeSourceRbacRelations = checkRela(relation);
                            if(cloudNativeSourceRbacRelations.size() == 0) {
                                cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveRoleBinding(CloudNativeSourceWithBLOBs cloudNativeSource) throws Exception {
        try {
            String json = cloudNativeSource.getSourceJson();
            if (!StringUtils.isEmpty(json)) {
                //1.插入role
                JSONObject sourceJson = JSONObject.parseObject(json);
                String roleRef = sourceJson.getString("roleRef");

                JSONObject roleJson = JSONObject.parseObject(roleRef);
                String roleKind = roleJson.getString("kind");
                String roleName = roleJson.getString("name");

                CloudNativeSourceWithBLOBs roleSource = new CloudNativeSourceWithBLOBs();
                roleSource.setSourceName(roleName);
                roleSource.setSourceNamespace(cloudNativeSource.getSourceNamespace());
                roleSource.setCloudNativeId(cloudNativeSource.getCloudNativeId());
                roleSource.setSourceType(roleKind);
                List<CloudNativeSourceWithBLOBs> roleSources = checkSource(roleSource);
                if (roleSources.size() > 0) {
                    roleSource = roleSources.get(0);
                } else {
                    return;
                }

                CloudNativeSourceRbacNode role = new CloudNativeSourceRbacNode();
                role.setId(UUIDUtil.newUUID());
                role.setName(roleSource.getSourceName());
                role.setNamespace(roleSource.getSourceNamespace());
                role.setK8sId(cloudNativeSource.getCloudNativeId());
                role.setValue(1);
                role.setSymbolsize(35);
                role.setCategory(CloudNativeConstants.K8S_TYPE.Role.name());
                role.setCreateTime(System.currentTimeMillis());
                List<CloudNativeSourceRbacNode> csrList = checkNode(role);
                if (csrList.size() > 0) {
                    role = csrList.get(0);
                } else {
                    role.setOrder(caluOrder(role));
                    cloudNativeSourceRbacNodeMapper.insertSelective(role);
                }

                //2.插入serviceAccount
                String subjects = sourceJson.getString("subjects");
                if (!StringUtils.isEmpty(subjects)) {
                    JSONArray jsonArray = JSONArray.parseArray(subjects);
                    int saNumbers = 0;
                    for (Object object : jsonArray) {
                        JSONObject subjectJson = (JSONObject) object;
                        String kind = subjectJson.getString("kind");
                        String name = subjectJson.getString("name");
                        String namespace = subjectJson.getString("namespace");
                        if (StringUtils.equalsIgnoreCase(kind, "ServiceAccount")) {
                            CloudNativeSourceWithBLOBs checkSource = new CloudNativeSourceWithBLOBs();
                            checkSource.setSourceName(name);
                            checkSource.setSourceNamespace(namespace);
                            checkSource.setCloudNativeId(cloudNativeSource.getCloudNativeId());
                            checkSource.setSourceType(CloudNativeConstants.K8S_TYPE.ServiceAccount.name());
                            List<CloudNativeSourceWithBLOBs> saSources = checkSource(checkSource);
                            if (saSources.size() > 0) {
                                saNumbers++;
                                CloudNativeSource saSource = saSources.get(0);
                                CloudNativeSourceRbacNode serviceAccount = new CloudNativeSourceRbacNode();
                                serviceAccount.setId(UUIDUtil.newUUID());
                                serviceAccount.setName(saSource.getSourceName());
                                serviceAccount.setNamespace(saSource.getSourceNamespace());
                                serviceAccount.setK8sId(cloudNativeSource.getCloudNativeId());
                                serviceAccount.setValue(1);
                                serviceAccount.setSymbolsize(50);
                                serviceAccount.setCategory(CloudNativeConstants.K8S_TYPE.ServiceAccount.name());
                                serviceAccount.setCreateTime(System.currentTimeMillis());

                                List<CloudNativeSourceRbacNode> csrList2 = checkNode(serviceAccount);
                                if (csrList2.size() > 0) {
                                    serviceAccount = csrList2.get(0);
                                } else {
                                    serviceAccount.setOrder(caluOrder(serviceAccount));
                                    cloudNativeSourceRbacNodeMapper.insertSelective(serviceAccount);

                                    CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                                    String linkId = UUIDUtil.newUUID();
                                    link.setId(linkId);
                                    link.setK8sId(cloudNativeSource.getCloudNativeId());
                                    link.setSource("0");
                                    link.setTarget(serviceAccount.getOrder().toString());
                                    link.setCreateTime(System.currentTimeMillis());
                                    cloudNativeSourceRbacLinkMapper.insertSelective(link);

                                    CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                                    relation.setId(UUIDUtil.newUUID());
                                    relation.setK8sId(cloudNativeSource.getCloudNativeId());
                                    relation.setLinkId(linkId);
                                    relation.setName("Manage");
                                    relation.setCreateTime(System.currentTimeMillis());
                                    cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                                }

                                CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                                String linkId = UUIDUtil.newUUID();
                                link.setId(linkId);
                                link.setK8sId(cloudNativeSource.getCloudNativeId());
                                link.setSource(serviceAccount.getOrder().toString());
                                link.setTarget(role.getOrder().toString());
                                link.setCreateTime(System.currentTimeMillis());
                                cloudNativeSourceRbacLinkMapper.insertSelective(link);

                                CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                                relation.setId(UUIDUtil.newUUID());
                                relation.setK8sId(cloudNativeSource.getCloudNativeId());
                                relation.setLinkId(linkId);
                                relation.setName("Associate");
                                relation.setCreateTime(System.currentTimeMillis());
                                cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                            }
                        }
                    }
                    if(saNumbers == 0) {
                        CloudNativeSourceRbacLink link = new CloudNativeSourceRbacLink();
                        String linkId = UUIDUtil.newUUID();
                        link.setId(linkId);
                        link.setK8sId(cloudNativeSource.getCloudNativeId());
                        link.setSource("0");
                        link.setTarget(role.getOrder().toString());
                        link.setCreateTime(System.currentTimeMillis());
                        cloudNativeSourceRbacLinkMapper.insertSelective(link);

                        CloudNativeSourceRbacRelation relation = new CloudNativeSourceRbacRelation();
                        relation.setId(UUIDUtil.newUUID());
                        relation.setK8sId(cloudNativeSource.getCloudNativeId());
                        relation.setLinkId(linkId);
                        relation.setName("Manage");
                        relation.setCreateTime(System.currentTimeMillis());
                        cloudNativeSourceRbacRelationMapper.insertSelective(relation);
                    }
                }

                //3.插入resource
                saveRole(roleSource);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private long caluOrder(CloudNativeSourceRbacNode cloudNativeSourceRbacNode) {
        CloudNativeSourceRbacNodeExample example = new CloudNativeSourceRbacNodeExample();
        example.createCriteria().andK8sIdEqualTo(cloudNativeSourceRbacNode.getK8sId());
        long order = cloudNativeSourceRbacNodeMapper.countByExample(example);
        return order;
    }

    public List<CloudNativeSourceWithBLOBs> checkSource(CloudNativeSourceWithBLOBs cloudNativeSourceWithBLOBs) throws Exception {
        try {
            CloudNativeSourceExample example = new CloudNativeSourceExample();
            example.createCriteria().andCloudNativeIdEqualTo(cloudNativeSourceWithBLOBs.getCloudNativeId()).andSourceNameEqualTo(cloudNativeSourceWithBLOBs.getSourceName()).andSourceNamespaceEqualTo(cloudNativeSourceWithBLOBs.getSourceNamespace()).andSourceTypeEqualTo(cloudNativeSourceWithBLOBs.getSourceType());
            List<CloudNativeSourceWithBLOBs> list = cloudNativeSourceMapper.selectByExampleWithBLOBs(example);
            return list;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<CloudNativeSourceRbacNode> checkNode(CloudNativeSourceRbacNode cloudNativeSourceRbacNode) throws Exception {
        try {
            CloudNativeSourceRbacNodeExample example = new CloudNativeSourceRbacNodeExample();
            example.createCriteria().andNameEqualTo(cloudNativeSourceRbacNode.getName()).andNamespaceEqualTo(cloudNativeSourceRbacNode.getNamespace()).andK8sIdEqualTo(cloudNativeSourceRbacNode.getK8sId()).andCategoryEqualTo(cloudNativeSourceRbacNode.getCategory());
            List<CloudNativeSourceRbacNode> list = cloudNativeSourceRbacNodeMapper.selectByExample(example);
            return list;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<CloudNativeSourceRbacLink> checkLink(CloudNativeSourceRbacLink cloudNativeSourceRbacLink) throws Exception {
        try {
            CloudNativeSourceRbacLinkExample example = new CloudNativeSourceRbacLinkExample();
            example.createCriteria().andSourceEqualTo(cloudNativeSourceRbacLink.getSource()).andTargetEqualTo(cloudNativeSourceRbacLink.getTarget()).andK8sIdEqualTo(cloudNativeSourceRbacLink.getK8sId());
            List<CloudNativeSourceRbacLink> list = cloudNativeSourceRbacLinkMapper.selectByExample(example);
            return list;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<CloudNativeSourceRbacRelation> checkRela(CloudNativeSourceRbacRelation cloudNativeSourceRbacRelation) throws Exception {
        try {
            CloudNativeSourceRbacRelationExample example = new CloudNativeSourceRbacRelationExample();
            example.createCriteria().andNameEqualTo(cloudNativeSourceRbacRelation.getName()).andLinkIdEqualTo(cloudNativeSourceRbacRelation.getLinkId()).andK8sIdEqualTo(cloudNativeSourceRbacRelation.getK8sId());
            List<CloudNativeSourceRbacRelation> list = cloudNativeSourceRbacRelationMapper.selectByExample(example);
            return list;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return new ArrayList<>();
        }
    }

}
