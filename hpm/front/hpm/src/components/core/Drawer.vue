<template>
  <v-navigation-drawer
    id="app-drawer"
    v-model="inputValue"
    app
    floating
    persistent
    mobile-break-point="991"
    width="260"
    absolute
    style="background: #eee;"
  >
    <v-img
      :src="image"
      height="100%"
    >
      <v-layout
        class="fill-height"
        tag="v-list"
        column
      >
        <v-list-tile
          avatar
          style="background: #287be4;"
        >
          <v-list-tile-avatar
            color="grey"
          >
            <v-img
              :src="logo"
              height="64"
              contain
            />
          </v-list-tile-avatar>
          <v-list-tile-title
            class="title"
            style="color:white;" >
            HPM
          </v-list-tile-title>
        </v-list-tile>
        <v-divider/>
        <v-list-tile
          v-for="(link, i) in authLink"
          :key="i"
          :to="link.to"
          :active-class="color"
          avatar
          class="v-list-item">
          <v-icon
            :color="iconColor(link.to)">{{ link.icon }}</v-icon>
          <v-list-tile-title
            :style="textColor(link.to)"
            v-text="link.text"/>
        </v-list-tile>
      </v-layout>
    </v-img>
  </v-navigation-drawer>
</template>

<script>
// Utilities
import {
  mapMutations,
  mapState
} from 'vuex'

export default {
  data: () => ({
    selected: 2,
    logo: require('@/assets/img/redditicon.png'),
    links: [
      // {
      //   to: '/',
      //   icon: 'mdi-view-dashboard',
      //   text: 'Dashboard'
      // },
      {
        to: '/dashboard/member',
        icon: 'mdi-account',
        text: '회원 관리',
        auth: ['ROLE_MANAGER']
      },
      {
        to: '/dashboard/customer',
        icon: 'mdi-account',
        text: '거래처 관리',
        auth: ['ROLE_MANAGER']
      },
      {
        to: '/dashboard/instrument/company',
        icon: 'mdi-account',
        text: '위탁업체 관리',
        auth: ['ROLE_MANAGER']
      },
      {
        to: '/dashboard/codeManage',
        icon: 'mdi-account',
        text: '코드 관리',
        auth: ['ROLE_MANAGER']
      },
      {
        to: '/dashboard/application',
        icon: 'mdi-account',
        text: '접수대장 관리',
        auth: ['ROLE_MANAGER', 'ROLE_USER']
      },
      {
        to: '/dashboard/consign',
        icon: 'mdi-account',
        text: '위탁업체 접수대장 관리',
        auth: ['ROLE_MANAGER', 'ROLE_USER']
      },
      {
        to: '/dashboard/appLog',
        icon: 'mdi-account',
        text: '접수대장 이력',
        auth: ['ROLE_MANAGER', 'ROLE_USER']
      }
      // {
      //   to: '/dashboard/roleManage',
      //   icon: 'mdi-account',
      //   text: '권한 관리'
      // },
      
    ],
    responsive: false
  }),
  computed: {
    ...mapState('app', ['image', 'color']),
    inputValue: {
      get () {
        return this.$store.state.app.drawer
      },
      set (val) {
        this.setDrawer(val)
      }
    },
    items () {
      return this.$t('Layout.View.items')
    },
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
  mounted () {
    this.onResponsiveInverted()
    window.addEventListener('resize', this.onResponsiveInverted)
  },
  beforeDestroy () {
    window.removeEventListener('resize', this.onResponsiveInverted)
  },
  beforeMount () {
    
  },
  methods: {
    ...mapMutations('app', ['setDrawer', 'toggleDrawer']),
    onResponsiveInverted () {
      if (window.innerWidth < 991) {
        this.responsive = true
      } else {
        this.responsive = false
      }
    },
    toggle (index) {
      this.selected = index
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
  }
}
</script>

<style lang="scss">
  #app-drawer {
    .v-list__tile {
      border-radius: 4px;

      &--buy {
        margin-top: auto;
        margin-bottom: 17px;
      }
    }

    .v-image__image--contain {
      top: 9px;
      height: 60%;
    }

    .search-input {
      margin-bottom: 30px !important;
      padding-left: 15px;
      padding-right: 15px;
    }
  }
</style>
