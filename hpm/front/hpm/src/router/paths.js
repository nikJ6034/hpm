/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
import store from '../store'
import AuthLayout from "@/layout/AuthLayout";
import Login from "../views/Login.vue";
import DashboardLayout from "@/layout/DashboardLayout";

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
    component: AuthLayout,
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
        component: Login
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
    component: DashboardLayout,
    children: [
      {
        path: '',
        name: 'Dashboard',
        component: () => import(`@/components/hpm/customer/Customer.vue`)
      },
      {
        path: 'profile',
        name: '개인정보 관리',
        component: () => import(`@/views/UserProfile.vue`)
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
        name: '위탁업체 접수대장',
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
        path: '/dashboard/issueLog',
        name: '접수대장',
        component: () => import(`@/components/hpm/issue/IssueLogView.vue`)
      },
      {
        path: '/dashboard/log',
        name: '대장이력',
        component: () => import(`@/components/hpm/log/LogView.vue`)
      }
    ]
  }
]
