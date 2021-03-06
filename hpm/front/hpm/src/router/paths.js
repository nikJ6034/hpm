/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
import store from '../store'

const requireManager = () => (to, from, next) => {
  if (!store.state.user.authorities) {
    next('/')
    return
  }
  if (store.state.user.authorities.includes('ROLE_MANAGER')) {
    return next()
  } else {
    next('/')
  }
}

export default [
  {
    path: '*',
    meta: {
      name: '',
      requiresAuth: true
    },
    redirect: {
      path: '/'
    }
  },
  // This  allows you to have pages apart of the app but no rendered inside the dash
  {
    path: '/',
    meta: {
      name: '',
      requiresAuth: false
    },
    component: () =>
      import(/* webpackChunkName: "routes" */ `@/views/LoginHome.vue`),
    // redirect if already signed in
    beforeEnter: (to, from, next) => {
      if (store.getters.authorized) {
        next('/dashboard/application')
      } else {
        next()
      }
    },
    children: [
      {
        path: '',
        component: () => import(`@/components/LoginForm.vue`)
      }
    ]
  },
  // add any extra routes that you want rendered in the dashboard as a child below. Change toolbar names here
  {
    path: '/dashboard',
    meta: {
      name: 'Dashboard View',
      requiresAuth: true
    },
    component: () => import(`@/views/DashboardView.vue`),
    children: [
      {
        path: '',
        name: 'Dashboard',
        component: () => import(`@/components/hpm/customer/Customer.vue`)
      },
      {
        path: 'member',
        name: '회원 관리',
        component: () => import(`@/components/hpm/member/Member.vue`),
        beforeEnter: requireManager()
      },
      {
        path: 'customer',
        name: '거래처 관리',
        component: () => import(`@/components/hpm/customer/Customer.vue`),
        beforeEnter: requireManager()
      },
      {
        path: 'instrument/company',
        name: '위탁업체 관리',
        component: () => import(`@/components/hpm/consignmentCompany/ConsignmentCompany.vue`),
        beforeEnter: requireManager()
      },
      {
        path: 'application',
        name: '신청서 관리',
        component: () => import(`@/components/hpm/application/Application.vue`)
      },
      {
        path: 'application/write',
        name: '신청서 작성',
        component: () => import(`@/components/hpm/application/ApplicationWrite.vue`)
      },
      {
        path: 'application/view',
        name: '신청서 상세',
        component: () => import(`@/components/hpm/application/ApplicationView.vue`)
      },
      {
        path: '/dashboard/appLog',
        name: '신청서 상세',
        component: () => import(`@/components/hpm/applicationLog/ApplicationLogView.vue`)
      },
      {
        path: 'consign',
        name: '위탁업체 신청서 관리',
        component: () => import(`@/components/hpm/consign/Consign.vue`)
      },
      {
        path: 'consign/write',
        name: '위탁업체 신청서 작성',
        component: () => import(`@/components/hpm/consign/ConsignWrite.vue`)
      },
      {
        path: 'roleManage',
        name: '권한 관리',
        component: () => import(`@/components/hpm/roleManage/RoleManage.vue`),
        beforeEnter: requireManager()
      },
      {
        path: 'codeManage',
        name: '코드 관리',
        component: () => import(`@/components/hpm/codeManage/CodeManage.vue`),
        beforeEnter: requireManager()
      },
      {
        path: 'user-profile',
        meta: {
          name: 'User Profile',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/UserProfile.vue`)
      },
      {
        path: 'table-list',
        meta: {
          name: 'Table List',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/SimpleTables.vue`)
      },
      {
        path: 'user-tables',
        meta: {
          name: 'User Table',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/UsersTable.vue`)
      },
      {
        path: 'tablestest',
        meta: {
          name: 'Complex Tables test',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/TableList.vue`)
      },
      {
        path: 'typography',
        meta: {
          name: 'Typography',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/Typography.vue`)
      },
      {
        path: 'icons',
        meta: {
          name: 'Icons',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/Icons.vue`)
      },
      {
        path: 'maps',
        meta: {
          name: 'Maps',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/Maps.vue`)
      },
      {
        path: 'notifications',
        meta: {
          name: 'Notifications',
          requiresAuth: true
        },
        component: () => import(`@/components/DashViews/Notifications.vue`)
      }
    ]
  }
]
