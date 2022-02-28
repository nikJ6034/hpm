<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <side-bar
      :background-color="sidebarBackground"
      short-title="Argon"
      title="Argon"
    >
      <template slot="links">
        <template 
          v-for="(alink, i) in authLink">
        <sidebar-item
          :key="i"
          :link="alink"
        />
      </template>
        <!--<sidebar-item :link="{name: 'Icons', icon: 'ni ni-planet text-blue', path: '/icons'}"/>
        <sidebar-item :link="{name: 'Maps', icon: 'ni ni-pin-3 text-orange', path: '/maps'}"/>
        <sidebar-item :link="{name: 'User Profile', icon: 'ni ni-single-02 text-yellow', path: '/profile'}"/>
        <sidebar-item :link="{name: 'Tables', icon: 'ni ni-bullet-list-67 text-red', path: '/tables'}"/>
        <sidebar-item :link="{name: 'Login', icon: 'ni ni-key-25 text-info', path: '/login'}"/>
        <sidebar-item :link="{name: 'Register', icon: 'ni ni-circle-08 text-pink', path: '/register'}"/>
        -->
      </template>
    </side-bar>
    <div class="main-content" :data="sidebarBackground">
      <dashboard-navbar></dashboard-navbar>

      <div style="background:#fff" @click="toggleSidebar">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <router-view></router-view>
        </fade-transition>
        <content-footer v-if="!$route.meta.hideFooter"></content-footer>
      </div>
    </div>
  </div>
</template>
<script>
  import DashboardNavbar from './DashboardNavbar.vue';
  import ContentFooter from './ContentFooter.vue';
  import { FadeTransition } from 'vue2-transitions';

  export default {
    components: {
      DashboardNavbar,
      ContentFooter,
      FadeTransition
    },
    data() {
      return {
        sidebarBackground: 'vue' //vue|blue|orange|green|red|primary
        ,links: [
          {
            path: '/dashboard/member',
            icon: 'ni ni-single-02',
            name: '회원 관리',
            auth: ['ROLE_MANAGER']
          },
          {
            path: '/dashboard/customer',
            icon: 'ni ni-bullet-list-67',
            name: '거래처 관리',
            auth: ['ROLE_MANAGER']
          },
          {
            path: '/dashboard/instrument/company',
            icon: 'ni ni-circle-08',
            name: '위탁업체 관리',
            auth: ['ROLE_MANAGER']
          },
          {
            path: '/dashboard/codeManage',
            icon: 'ni ni-settings',
            name: '코드 관리',
            auth: ['ROLE_MANAGER']
          },
          {
            path: '/dashboard/application',
            icon: 'ni ni-single-copy-04',
            name: '신청서 작성',
            auth: ['ROLE_MANAGER', 'ROLE_USER']
          },
          {
            path: '/dashboard/consign',
            icon: 'ni ni-single-copy-04',
            name: '위탁업체 신청서 작성',
            auth: ['ROLE_MANAGER', 'ROLE_USER']
          },
          {
            path: '/dashboard/appLog',
            icon: 'ni ni-bold-right',
            name: '위탁업체 접수대장 이력',
            auth: ['ROLE_MANAGER', 'ROLE_USER']
          },
          {
            path: '/dashboard/issueLog',
            icon: 'ni ni-bold-right',
            name: '접수대장 이력',
            auth: ['ROLE_MANAGER', 'ROLE_USER']
          },
          {
            path: '/dashboard/log',
            icon: 'ni ni-align-left-2',
            name: '대장이력',
            auth: ['ROLE_MANAGER', 'ROLE_USER']
          } 
        ],
      };
    },
    methods: {
      toggleSidebar() {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false);
        }
      },
      iconColor (page) {
        if (page.indexOf(this.$route.path) > -1) {
          return 'white'
        } else {
          return 'normal'
        }
      },
      textColor (page) {
        if (this.$route.path.indexOf(page) > -1) {
          return 'color:white;'
        } else {
          return 'color:black;'
        }
      },
      isAuth: function(auth){
        var result = false
        const _this = this
        if(!this.$store.state.user.authorities){
          return false
        }
        auth.forEach(function(item){
          result = _this.$store.state.user.authorities.includes(item) || result
        })
          return result
      }
    },
    computed: {
      authLink (){
        const linkList = [];
        const _this = this;
        this.links.forEach(function(item){
          if(_this.isAuth(item.auth)){
            linkList.push(item)
          }
        })
        return linkList
      }
    },
  };
</script>
<style lang="scss">
</style>
