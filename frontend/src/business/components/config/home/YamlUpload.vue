<template>
  <div>
    <!--文件上传入口-->
    <!-- 上传组件 -->
    <el-upload action drag :auto-upload="true" :on-change="handleChange" :http-request="submit"
               :before-upload="beforeUpload" :on-exceed="handleExceed" ref="path" :file-list="fileList" :limit="1">
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">{{ $t('package.upload_text1') }}<em>{{ $t('package.upload_text2') }}</em></div>
      <div class="el-upload__tip" slot="tip">{{ $t('image.upload_tip2', ['500M']) }}</div>
      <div class="el-upload__tip content" slot="tip">
        <span>{{ 'format (*.yaml)' }}</span>
      </div>
    </el-upload>

  </div>
</template>
<script>
export default {
  name: "YamlUpload",
  data(){
    return {
      loading:false,
      // 文件类型, 例如
      // Tar archive format (*.tar);
      fileType: ['yaml'],
      // 大小限制(MB)
      fileSize: 500,
      fileList: [],
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      uploadSuccess: true,
    }
  },
  created() {
  },
  methods:{
    handleChange(file, fileList) { //文件数量改变
      if(this.fileList.length>1) return;
      this.fileList = fileList;
      this.uploadValidate(file);
    },
    beforeUpload(file){
      this.uploadValidate(file);
    },
    uploadValidate(file) {
      // 校检文件类型
      if (this.fileType) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }

        const isTypeOk = this.fileType.some((type) => {
          if (file.type?file.type.indexOf(type):file.name.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
        if (!isTypeOk) {
          this.$message.error(this.$t('commons.adv_search.file_type_warn') + this.fileType.join("/") + this.$t('commons.adv_search.file_type_warn2'));
          this.uploadSuccess = false;
          return false;
        }
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$message.error(this.$t('commons.file_size_warn') + this.fileSize + ' MB!');
          this.uploadSuccess = false;
          return false;
        }
      }
    },
    handleExceed(){
    },
    submit(file) {
      if(!this.uploadSuccess) return;
      this.$fileUpload("/config/uploadYaml", file.file, null, {}, response => {
        if(response.success) {
          this.$message({
            message: this.$t("commons.save_success"),
            duration: 1000
          });
          this.$emit('appendYaml', response.data);
        } else {
          this.$message({
            message: this.$t("commons.save_failed"),
            duration: 1000
          });
        }
      });
    }
  }
}
</script>
<style scoped>

.content {
  color: red;
}
</style>
