<template>
  <div>
    <el-popover
      ref="popover"
      placement="left"
      width="400"
      trigger="hover">
      <el-table :border="true" :stripe="true" :data="string2PrettyFormat" class="adjust-table table-content">
          <el-table-column type="index" min-width="10%"/>
          <el-table-column prop="region" :label="$t('account.region_id')" min-width="25%"></el-table-column>
          <el-table-column prop="regionName" :label="$t('account.region_name')" min-width="25%"></el-table-column>
          <el-table-column prop="dataCount" :label="$t('event.data_count')" min-width="25%"></el-table-column>
      </el-table>
      <el-button slot="reference" size="mini" type="primary" plain @click="showRegions">
        {{ $t('account.regions') }}
      </el-button>
    </el-popover>

    <!--regions-->
    <el-drawer class="rtl" :title="$t('account.regions')" :visible.sync="regionsVisible" size="45%" :before-close="handleClose" :direction="direction"
               :destroy-on-close="true">
      <el-table :border="true" :stripe="true" :data="string2PrettyFormat" class="adjust-table table-content">
        <el-table-column type="index" min-width="10%"/>
        <el-table-column prop="region" :label="$t('account.region_id')" min-width="45%"></el-table-column>
        <el-table-column prop="regionName" :label="$t('account.region_name')" min-width="45%"></el-table-column>
        <el-table-column prop="dataCount" :label="$t('event.data_count')" min-width="45%"></el-table-column>
      </el-table>
    </el-drawer>
    <!--regions-->
  </div>
</template>

<script>
  /* eslint-disable */
  export default {
    name: "Regions",
    props: {
      logId: Number,
      accountId: String,
    },
    data() {
      return {
        string2PrettyFormat: [],
        regionsVisible: false,
        direction: 'rtl',
      }
    },
    created() {
      let url = "/cloud/event/sync/log/region/list/" + this.logId;
      this.result = this.$post(url, {}, response => {
        this.string2PrettyFormat = response.data
      })

    },
    methods: {
      showRegions() {
        this.regionsVisible =  true;
      },
      handleClose() {
        this.regionsVisible =  false;
      },
    },
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
  .code-mirror {
    height: 600px !important;
  }
  .code-mirror >>> .CodeMirror {
    /* Set height, width, borders, and global font properties here */
    height: 600px !important;
  }
  /deep/ :focus{outline:0;}
</style>
