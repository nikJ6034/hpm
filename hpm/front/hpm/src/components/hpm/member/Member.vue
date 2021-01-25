<template>
  <v-container
    fill-height
    fluid
    grid-list-xl>
    <v-layout
      justify-center
      wrap>
      <b-container fluid>
        <b-row
          sm
          class="text-center"
          style="padding: 0 0 0 10px;">
          <b-col
            xl="6"
            style="padding:0 5px 0 5px">
            <b-container
              style="min-height: 350px; max-height: 750px;">
              <b-row>
                <b-col class="f25">회원 목록</b-col>
              </b-row>
              <hr>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    v-model="condition"
                    class="mb-3">
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="id">아이디</b-form-select-option>
                    <b-form-select-option value="name">이름</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    v-model="keyword"
                    type="text"
                    placeholder="검색조건을 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    variant="outline-primary"
                    @click="search"
                  >조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :striped="striped"
                  :items="members"
                  :fields="fields"
                  class="pointer"
                  hover
                  @row-clicked="companyClick"
                />
              </b-row>
              <b-row class="d-inline-block">
                <b-pagination
                  v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  align="center"
                  @page-click="pageSearch"
                />
              </b-row>
            </b-container>
          </b-col>
          <b-col
            :style="rightTbl"
            xl="6">
            <b-container
              style="min-height: 350px; max-height: 750px;">
              <b-row>
                <b-col class="f25">회원 {{ titleInfo }}</b-col>
              </b-row>
              <hr>
              <b-row
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">
                  <span style="color:red;">*</span>
                  ID
                </b-col>
                <b-col
                  cols="9">
                  <b-form-input
                    :readonly="member.id != null"
                    v-model="member.memberId"
                    placeholder="ID를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">
                  <span style="color:red;">*</span>
                  이름
                </b-col>
                <b-col
                  cols="9">
                  <b-form-input
                    :readonly="member.id != null"
                    v-model="member.name"
                    placeholder="이름을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                v-if="member.id == null"
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right"><span style="color:red;">*</span> 비밀번호
                </b-col>
                <b-col
                  cols="9">
                  <b-form-input
                    v-model="member.memberPassword"
                    type="password"
                    placeholder="비밀번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                v-if="member.id == null"
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">비밀번호 확인
                </b-col>
                <b-col
                  cols="9">
                  <b-form-input
                    v-model="member.passwordConfirm"
                    type="password"
                    placeholder="비밀번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">이메일주소
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="member.email"
                    placeholder="이메일주소" />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">전화번호
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="member.mobile"
                    placeholder="전화번호" />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3"
                  class="mFormLbl text-right">권한
                </b-col>
                <b-col cols="9">
                  <b-form-select
                    v-model="member.role.id"
                    :options="roleList"
                    value-field="id"
                    text-field="roleDesc"
                  />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col>
                  <b-modal
                    v-model="modalShow"
                    title="비밀번호 변경">
                    @ok="changPassword"
                    <b-form-input
                      v-model="member.memberPassword"
                      type="password"
                      placeholder="비밀번호를 입력하세요." />
                    <b-form-input
                      v-model="member.passwordConfirm"
                      type="password"
                      placeholder="비밀번호 확인" />
                  </b-modal>
                  <b-button
                    v-if="member.id != null"
                    class="float-right"
                    variant="outline-primary"
                    @click="cancel">취소</b-button>
                  <b-button
                    v-if="member.id == null"
                    class="float-right"
                    variant="outline-primary"
                    @click="register">등록</b-button>
                  <b-button
                    v-if="member.id != null"
                    class="float-right mr-3"
                    variant="outline-danger"
                    @click="memeberDelete">삭제</b-button>
                  <b-button
                    v-if="member.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="memberModify">수정</b-button>
                  <b-button
                    v-if="member.id != null"
                    class="float-right mr-3"
                    variant="outline-primary"
                    @click="modalShow = !modalShow">비밀번호 수정</b-button>
                </b-col>
              </b-row>
            </b-container>
          </b-col>
        </b-row>
      </b-container >
    </v-layout>
  </v-container>
</template>

<script>

export default {
  data: () => ({
    fields: [ { key: 'memberId', label: 'ID' }, { key: 'name', label: '이름' }, { key: 'email', label: '이메일' }, { key: 'mobile', label: '전화번호' }, { key: 'role.roleDesc', label: '권한' } ],
    members: null,
    test: '1111',
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition: '',
    striped: false,
    member: { id: null, name: null, email: null, mobile: null, memberId: null, memberPassword: null, passwordConfirm: null, role: { id: null } },
    roleList: null,
    modalShow: false,
    rightTbl: {
      padding: '0 5px 0 0',
      borderLeft: '1px solid lightgrey',
      height: '750px'
    }
  }),
  computed: {
    titleInfo: function () {
      if (!this.member.id) {
        return '등록'
      }
      return '정보'
    }
  },
  beforeMount () {
      this.search()
      this.getRoleList()
  },
  mounted () {
     window.addEventListener('resize', this.handleResize)
  },
  methods: {
    handleResize (event) {
        if (window.innerWidth < 1330) {
          this.rightTbl.borderLeft = '0px'
          this.rightTbl.height = '350px'
        } else {
          this.rightTbl.borderLeft = '1px solid lightgrey'
          this.rightTbl.height = '750px'
        }
    },
    companyClick: function (item) {
      this.member = JSON.parse(JSON.stringify(item))
      if (item.role) {
        this.member.role.id = item.role.id || null
      } else {
        this.member.role.id = null
      }
    },
    cancel: function () {
      this.member = { id: null, name: null, email: null, mobile: null, memberId: null, memberPassword: null, passwordConfirm: null, role: { id: null } }
    },
    register: function () {
      if (confirm('회원을 등록하시겠습니까?')) {
      this.$http.post(`/api/member`, this.member)
      .then(response => {
        if (response.data.result === 'success') {
          alert('회원가입 되었습니다.')
          this.search(this.currentPage)
        }
        })
      }
    },
    memberModify () {
      if (this.member.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }
      if (this.member.memberId == null) {
        alert('아이디를 입력해주세요.')
        return false
      }
      this.$http.put(`/api/member`, this.member)
      .then(response => {
        if (response.data.result === 'success') {
          alert('회원정보가 변경되었습니다.')
          this.search(this.currentPage)
        }
        })
    },
    search (num) {
      const page = num - 1 | 0
      this.$http.get(`/api/member?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.members = response.data.content
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number + 1
        })
    },
    pageSearch (bvEvent, page) {
      this.search(page)
    },
    async getRoleList () {
      await this.$http.get(`/api/role`)
      .then(response => {
        this.roleList = response.data
        })
    },
    passwordCheck () {
      if (this.member.memberPassword !== this.member.passwordConfirm) {
        alert('비밀번호가 일치하지 않습니다.')
        return false
      } else if (this.member.memberPassword.length <= 10) {
        alert('비밀번호는 10자리 이상이어야합니다.')
        return false
      }
      return true
    },
    changPassword (bvModalEvt) {
      if (!this.passwordCheck()) {
        bvModalEvt.preventDefault()
        return false
      }
      this.$http.put(`/api/member/changePassword`, this.member)
      .then(response => {
        if (response.data.result === 'success') {
          alert('비밀번호가 변경되었습니다.')
          this.search(this.currentPage)
        } else {
          bvModalEvt.preventDefault()
        }
        })
    },
    memeberDelete () {
      if (confirm('정말 회원을 삭제하시겠습니까?')) {
      this.$http.delete(`/api/member`, { data: this.member })
      .then(response => {
        if (response.data.result === 'success') {
          alert('회원이 삭제되었습니다.')
          this.search(this.currentPage)
          this.cancel()
        }
        })
      }
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
