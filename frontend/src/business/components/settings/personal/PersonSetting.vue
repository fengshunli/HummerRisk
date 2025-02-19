<template>
  <div v-loading="result.loading">
    <el-card class="table-card">
      <template v-slot:header>
        <div>
          <el-row type="flex" just ify="space-between" align="middle">
            <span class="title">{{ $t('commons.personal_info') }}</span>
          </el-row>
        </div>
      </template>

      <!--Personal information menu-->
      <el-table border class="adjust-table" :data="tableData" style="width: 100%">
        <el-table-column type="index" min-width="50"/>
        <el-table-column prop="id" label="ID" min-width="100"/>
        <el-table-column prop="name" :label="$t('commons.username')" min-width="110"/>
        <el-table-column prop="email" :label="$t('commons.email')" min-width="200"/>
        <el-table-column prop="phone" :label="$t('commons.phone')" min-width="150"/>
        <el-table-column prop="createTime" :label="$t('commons.create_time')" min-width="160">
          <template v-slot:default="scope">
            <span>{{ scope.row.createTime | timestampFormatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('commons.operating')" fixed="right" min-width="100">
          <template v-slot:default="scope">
            <table-operator-button :tip="$t('member.edit_information')" icon="el-icon-edit"
                                      type="primary" @exec="edit(scope.row)"/>
            <table-operator-button :tip="$t('member.edit_password')" icon="el-icon-s-tools"
                                      type="success" @exec="editPassword(scope.row)"/>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--Modify personal details-->
    <el-drawer class="rtl" :title="$t('member.modify_personal_info')" :visible.sync="updateVisible" size="40%" :before-close="handleClose" :direction="direction"
               :destroy-on-close="true">
      <el-form :model="form" label-position="right" label-width="100px" size="small" :rules="rule"
               ref="updateUserForm">
        <el-form-item label="ID" prop="id">
          <el-input v-model="form.id" autocomplete="off" :disabled="true"/>
        </el-form-item>
        <el-form-item :label="$t('commons.username')" prop="name">
          <el-input v-model="form.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item :label="$t('commons.email')" prop="email">
          <el-input v-model="form.email" autocomplete="off"/>
        </el-form-item>
        <el-form-item :label="$t('commons.phone')" prop="phone">
          <el-input v-model="form.phone" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <dialog-footer
        @cancel="updateVisible = false"
        @confirm="updateUser('updateUserForm')"/>
    </el-drawer>

    <!--Change personal password-->
    <el-drawer class="rtl" :title="$t('member.edit_password')" :visible.sync="editPasswordVisible" size="45%" :before-close="handleClose" :direction="direction"
               :destroy-on-close="true">
      <el-form :model="ruleForm" :rules="rules" ref="editPasswordForm" label-width="120px" class="demo-ruleForm">
        <el-form-item :label="$t('member.old_password')" prop="password" style="margin-bottom: 29px">
          <el-input v-model="ruleForm.password" autocomplete="off" show-password/>
        </el-form-item>
        <el-form-item :label="$t('member.new_password')" prop="newpassword">
          <el-input v-model="ruleForm.newpassword" autocomplete="off" show-password/>
        </el-form-item>
        <el-form-item :label="$t('member.repeat_password')" prop="repeatPassword">
          <el-input v-model="ruleForm.repeatPassword" autocomplete="off" show-password/>
        </el-form-item>
      </el-form>
       <dialog-footer
         @cancel="editPasswordVisible = false"
         @confirm="updatePassword('editPasswordForm')"/>
    </el-drawer>


  </div>
</template>

<script>
import {TokenKey} from "@/common/js/constants";
import DialogFooter from "../../common/components/DialogFooter";
import {getCurrentUser, listenGoBack, removeGoBackListener} from "@/common/js/utils";
import TableOperatorButton from "../../common/components/TableOperatorButton";
/* eslint-disable */
  export default {
    name: "PersonSetting",
    components: {DialogFooter, TableOperatorButton},
    data() {
      return {
        result: {},
        updateVisible: false,
        editPasswordVisible: false,
        tableData: [],
        updatePath: '/user/update/current',
        updatePasswordPath: '/user/update/password',
        form: {},
        ruleForm: {},
        direction: 'rtl',
        rule: {
          name: [
            {required: true, message: this.$t('member.input_name'), trigger: 'blur'},
            {min: 2, max: 20, message: this.$t('commons.input_limit', [2, 20]), trigger: 'blur'},
            {
              required: true,
              pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9.·-]+$/,
              message: this.$t('member.special_characters_are_not_supported'),
              trigger: 'blur'
            }
          ],
          phone: [
            {
              required: false,
              pattern: '^1(3|4|5|7|8)\\d{9}$',
              message: this.$t('member.mobile_number_format_is_incorrect'),
              trigger: 'blur'
            }
          ],
          email: [
            {required: true, message: this.$t('member.input_email'), trigger: 'blur'},
            {
              required: true,
              pattern: /^([A-Za-z0-9_\-.])+@([A-Za-z0-9]+\.)+[A-Za-z]{2,6}$/,
              message: this.$t('member.email_format_is_incorrect'),
              trigger: 'blur'
            }
          ],
        },
        rules:{
          password: [
            {required: true, message: this.$t('user.input_password'), trigger: 'blur'},
          ],
          newpassword: [
            {required: true, message: this.$t('user.input_password'), trigger: 'blur'},
            {
              required: true,
              pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,30}$/,
              message: this.$t('member.password_format_is_incorrect'),
              trigger: 'blur'
            },
          ],
          repeatPassword: [
            {required: true, message: this.$t('user.input_password'), trigger: 'blur'},
            {
              required: true,
              pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,30}$/,
              message: this.$t('member.password_format_is_incorrect'),
              trigger: 'blur'
            },
          ]
        }
      }
    },

    activated() {
      this.initTableData();
    },
    methods: {
      currentUser: () => {
        return getCurrentUser();
      },
      edit(row) {
        this.updateVisible = true;
        this.form = Object.assign({}, row);
        listenGoBack(this.handleClose);
      },
      editPassword(row) {
        this.ruleForm.id = row.id;
        this.editPasswordVisible = true;
        listenGoBack(this.handleClose);
      },
      cancel(){
        this.editPasswordVisible = false;
        this.ruleForm.password="";
        this.ruleForm.newpassword="";
      },
      closeDialog(){
        this.editPasswordVisible = false;
        this.ruleForm.password="";
        this.ruleForm.newpassword="";
      },
      updateUser(updateUserForm) {
        this.$refs[updateUserForm].validate(valid => {
          if (valid) {
            this.result = this.$post(this.updatePath, this.form, response => {
              this.$success(this.$t('commons.modify_success'));
              localStorage.setItem(TokenKey, JSON.stringify(response.data));
              this.updateVisible = false;
              this.initTableData();
              window.location.reload();
            });
          } else {
            return false;
          }
        })
      },
      updatePassword(editPasswordForm) {
        this.$refs[editPasswordForm].validate(valid => {
          if (valid) {
            if (this.ruleForm.newpassword !== this.ruleForm.repeatPassword) {
              this.$warning(this.$t('member.inconsistent_passwords'));
              return;
            }
            this.result = this.$post(this.updatePasswordPath, this.ruleForm, response => {
              this.$success(this.$t('commons.modify_success'));
              this.editPasswordVisible = false;
              this.initTableData();
              window.location.reload();
            });
          } else {
            return false;
          }
        })
      },
      initTableData() {
        this.result = this.$get("/user/info/" + encodeURIComponent(this.currentUser().id), response => {
          let data = response.data;
          let dataList = [];
          dataList[0] = data;
          this.tableData = dataList;
        })
      },
      handleClose() {
        this.form = {};
        this.ruleForm = {};
        removeGoBackListener(this.handleClose);
        this.editPasswordVisible = false;
        this.updateVisible = false;
      }
    }
  }
</script>

<style scoped>
  .rtl >>> .el-drawer__body {
    overflow-y: auto;
    padding: 20px;
  }
  .rtl >>> input {
    width: 100%;
  }
  .rtl >>> .el-select {
    width: 80%;
  }
  .rtl >>> .el-form-item__content {
    width: 60%;
  }
  /deep/ :focus{outline:0;}
</style>
