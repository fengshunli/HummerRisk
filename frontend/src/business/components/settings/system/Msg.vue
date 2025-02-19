<template>
  <div v-loading="result.loading">

    <el-card class="table-card">
      <el-radio-group v-model="selectType" style="margin: 10px 0 10px 0;" @change="typeChange">
        <el-radio-button v-for="(item,index) in msgTypes" :key="index" class="de-msg-radio-class" :label="item.value">
          {{ $t(item.label) }}
        </el-radio-button>
      </el-radio-group>
      <el-row style="margin: 0 5px 10px 5px;">
        <el-button size="mini" :disabled="selectIds.length===0" @click="markReaded">{{ $t('webmsg.mark_readed') }}</el-button>
        <el-button size="mini" :disabled="selectIds.length===0" @click="deleteBatch">{{ $t('commons.delete') }}</el-button>
      </el-row>

      <table-header :condition.sync="condition" @search="search"
                    :title="$t('commons.proxy')"
                    :items="items" :columnNames="columnNames" :show-open="false"
                    :checkedColumnNames="checkedColumnNames" :checkAll="checkAll" :isIndeterminate="isIndeterminate"
                    @handleCheckedColumnNamesChange="handleCheckedColumnNamesChange" @handleCheckAllChange="handleCheckAllChange"/>

      <hide-table
        :table-data="tableData"
        @sort-change="sort"
        @filter-change="filter"
        @select-all="select"
        @select="select"
      >
        <el-table-column type="selection" min-width="40">
        </el-table-column>
        <el-table-column type="index" min-width="40"/>
        <el-table-column prop="content" v-if="checkedColumnNames.includes('content')" :label="$t('webmsg.content')" min-width="260">
          <template slot-scope="scope">
            <span style="display: flex;flex: 1;" @click="setReaded(scope.row)">
              <span>
                <i v-if="!scope.row.status" class="el-icon-message" style="color: red;" />
                <i v-else class="el-icon-message"/>
              </span>
              <span style="margin-left: 6px;" class="de-msg-a">
                {{ scope.row.content }}
              </span>
            </span>
          </template>
        </el-table-column>

        <el-table-column prop="createTime" v-if="checkedColumnNames.includes('createTime')" sortable="custom" :label="$t('webmsg.sned_time')" min-width="150">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | timestampFormatDate }}</span>
          </template>
        </el-table-column>

        <el-table-column prop="type" sortable="custom" v-if="checkedColumnNames.includes('type')" :label="$t('webmsg.type')" min-width="140">
          <template slot-scope="scope">
            <span>{{ scope.row.type }}</span>
          </template>
        </el-table-column>

        <el-table-column prop="scanType" sortable="custom" v-if="checkedColumnNames.includes('scanType')" :label="$t('system.plugin_scan_type')" min-width="120">
          <template slot-scope="scope">
            <span>{{ scope.row.scanType }}</span>
          </template>
        </el-table-column>

<!--        <el-table-column min-width="50" id="fixed" :label="$t('commons.operating')" prop="operating" type="operating" fixed="right">-->
<!--          <template v-slot:default="scope">-->
<!--            <table-operators :buttons="buttons" :row="scope.row"/>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </hide-table>
      <table-pagination :change="search" :current-page.sync="paginationConfig.currentPage" :page-size.sync="paginationConfig.pageSize" :total="paginationConfig.total"/>
    </el-card>
  </div>
</template>

<script>

import bus from '@/common/js/bus';
import TablePagination from "@/business/components/common/pagination/TablePagination";
import HideTable from "@/business/components/common/hideTable/HideTable";
import {MSG_CONFIGS} from "../../common/components/search/search-components";
import TableHeader from "@/business/components/common/components/TableHeader";
import {_filter, _sort} from "@/common/js/utils";
import TableOperators from "@/business/components/common/components/TableOperators";

//列表展示与隐藏
const columnOptions = [
  {
    label: 'webmsg.content',
    props: 'content',
    disabled: false
  },
  {
    label: 'webmsg.sned_time',
    props: 'createTime',
    disabled: false
  },
  {
    label: 'webmsg.type',
    props: 'type',
    disabled: false
  },
  {
    label: 'system.plugin_scan_type',
    props: 'scanType',
    disabled: false
  },
];

export default {
  name: "Msg",
  components: {
    TablePagination,
    HideTable,
    TableHeader,
    TableOperators,
  },
  data() {
    return {
      selectType: 2,
      result: {},
      msgTypes: [
        { value: 2, label: this.$t('webmsg.all_msg') },
        { value: 0, label: this.$t('webmsg.unread_msg') },
        { value: 1, label: this.$t('webmsg.read_msg') }
      ],
      tableData: [],
      condition: {
        components: MSG_CONFIGS
      },
      columns: [],
      paginationConfig: {
        currentPage: 1,
        pageSize: 10,
        total: 0
      },
      orderConditions: [],
      selectIds: [],
      loading: false,
      buttons: [
        {
          tip: this.$t('commons.detail'), icon: "el-icon-document", type: "success",
          exec: this.details
        },
      ],
      //名称搜索
      items: [
        {
          name: 'webmsg.content',
          id: 'content',
        },
        {
          name: 'webmsg.type',
          id: 'type',
        },
        {
          name: 'system.plugin_scan_type',
          id: 'scanType',
        },
      ],
      checkedColumnNames: columnOptions.map((ele) => ele.props),
      columnNames: columnOptions,
      checkAll: true,
      isIndeterminate: false,
    }
  },
  mounted() {
    this.selectIds = [];
    this.search()
  },
  created() {
  },
  methods: {
    handleCheckedColumnNamesChange(value) {
      const checkedCount = value.length;
      this.checkAll = checkedCount === this.columnNames.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.columnNames.length;
      this.checkedColumnNames = value;
    },
    handleCheckAllChange(val) {
      this.checkedColumnNames = val ? this.columnNames.map((ele) => ele.props) : [];
      this.isIndeterminate = false;
      this.checkAll = val;
    },
    select(selection) {
      this.selectIds = [];
      selection.forEach(s => {
        this.selectIds.push(s.id);
      });
    },
    sort(column) {
      _sort(column, this.condition);
      this.search();
    },
    filter(filters) {
      _filter(filters, this.condition);
      this.search();
    },
    search() {
      const { currentPage, pageSize } = this.paginationConfig;
      if (this.selectType === 2) {
        this.condition.status = null;
      } else if(this.selectType === 1) {
        this.condition.status = true;
      } else if(this.selectType === 0) {
        this.condition.status = false;
      }
      this.result = this.$post('/webmsg/list/' + currentPage + '/' + pageSize, this.condition, response => {
        this.tableData = response.data.listObject;
        this.paginationConfig.total = response.data.itemCount;
      });
    },
    typeChange(value) {
      if(value === 2) {
        this.condition.status = null;
      } else if(value === 1) {
        this.condition.status = true;
      } else if(value === 0) {
        this.condition.status = false;
      }
      this.search();
    },
    // 设置已读
    setReaded(row) {
      this.$get('/webmsg/setReaded/' + row.id, response => {
        bus.$emit('refresh-top-notification');
        this.selectIds = [];
        this.search();
      });
    },
    markReaded() {
      if (this.selectIds.length === 0) {
        this.$warning(this.$t('webmsg.please_select'));
        return;
      }
      const param = this.selectIds;
      this.$post('/webmsg/batchRead', param, response => {
        this.selectIds = [];
        this.search();
      });
    },
    deleteBatch() {
      if (this.selectIds.length === 0) {
        this.$warning(this.$t('webmsg.please_select'));
        return;
      }
      const param = this.selectIds;
      this.$post('/webmsg/batchDelete', param, response => {
        this.selectIds = [];
        this.search();
      });
    },
    detail(item) {
      if(!item.resultId) {
        this.$warning(this.$t('webmsg.can_not_conn'));
        return;
      }

    }
  }

}
</script>

<style scoped>
.de-msg-radio-class {
  padding: 0 5px;
}
>>>.el-radio-button__inner {
  border-radius: 4px 4px 4px 4px !important;
  border-left: 1px solid #dcdfe6 !important;
  padding: 10px 10px;
}
>>>.el-radio-button__orig-radio:checked+.el-radio-button__inner {
  color: #fff;
  background-color: #0a7be0;
  border-color: #0a7be0;
  -webkit-box-shadow: 0 0 0 0 #0a7be0;
  box-shadow: 0 0 0 0 #0a7be0;
}
.de-msg-a:hover {
    text-decoration: underline !important;
    color: #0a7be0 !important;
    cursor: pointer !important;
}

</style>
