<template>
  <div id="menu-bar" v-if="isRouterAlive">
    <el-row type="flex">
      <el-col :span="24">
        <el-menu class="header-menu" :unique-opened="true" mode="horizontal" router :default-active='$route.path'>

          <el-menu-item :index="'/oss/overview'">
            {{ $t("oss.oss_overview") }}
          </el-menu-item>

          <el-menu-item :index="'/oss/account'">
            {{ $t("oss.oss_account") }}
          </el-menu-item>

          <el-menu-item :index="'/oss/bucket'">
            {{ $t("oss.oss_bucket") }}
          </el-menu-item>

          <el-menu-item :index="'/oss/ossRisk'">
            {{ $t("oss.oss_risk") }}
          </el-menu-item>

          <el-menu-item :index="'/oss/ossReport'">
            {{ $t("oss.oss_report") }}
          </el-menu-item>

        </el-menu>
      </el-col>
    </el-row>
  </div>

</template>

<script>

export default {
  name: "HeaderMenus",
  components: {},
  data() {
    return {
      isRouterAlive: true,
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
    init() {
    },
  },
  watch: {
    '$route'(to) {
      this.init();
    }
  },
  mounted() {
    this.init();
  }
}

</script>

<style scoped>
#menu-bar {
  border-bottom: 1px solid #E6E6E6;
  background-color: #FFF;
}

</style>
