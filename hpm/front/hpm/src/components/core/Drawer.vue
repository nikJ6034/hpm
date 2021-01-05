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
  >
    <v-img
      :src="image"
      height="100%"
    >
      <v-layout
        class="fill-height"
        tag="v-list"
        column
        style="background:white"
        
      >
        <v-list-tile avatar
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
          <v-list-tile-title class="title" style="color:white;" >
            HPM
          </v-list-tile-title>
        </v-list-tile>
        <v-divider/>

        <v-list-tile
          v-for="(link, i) in links"
          :key="i"
          :to="link.to"
          :active-class="color"
          avatar
          class="v-list-item"
        >
        <v-icon :color="iconColor(link.to)"
          >{{ link.icon }}</v-icon>
        <v-list-tile-title v-text="link.text"
          :style="textColor(link.to)"
        />
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
    selected : 2,
    logo: require('@/assets/img/redditicon.png'),
    links: [
      {
        to: '/',
        icon: 'mdi-view-dashboard',
        text: 'Dashboard'
      },
      {
        to: '/dashboard/member',
        icon: 'mdi-account',
        text: '회원 관리'
      },
      {
        to: '/dashboard/company',
        icon: 'mdi-account',
        text: '사업자 관리'
      },
      {
        to: '/dashboard/instrument/company',
        icon: 'mdi-account',
        text: '계측사업자 관리'
      },
      {
        to: '/dashboard/application',
        icon: 'mdi-account',
        text: '접수대장 관리'
      }
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
    toggle(index) {
      this.selected = index;
    },
    iconColor (page) {
      if(page.indexOf(this.$route.path) > -1){
        return 'white'
      }else{
        return 'normal'
      }
    },
    textColor (page) {
      if(this.$route.path.indexOf(page) > -1){
        return 'color:white;'
      }else{
        return 'color:black;'
      }
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
