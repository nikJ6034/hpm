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
          class="text-center"
          style="padding: 0 0 0 10px;">
          <b-col
            xl="6"
            style="padding:0 5px 0 5px">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">위탁업체 목록</b-col>
              </b-row>
              <hr />
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    v-model="condition"
                    class="mb-3">
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="name">거래처명</b-form-select-option>
                    <b-form-select-option value="companyRegNumber">사업자번호</b-form-select-option>
                    <b-form-select-option value="tel">전화번호</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    type="text"
                    v-model="keyword"
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
                  :items="members"
                  :fields="fields"
                  @row-clicked="companyClick"
                  class="pointer"
                  hover
                  />
              </b-row>
              <b-row class="d-inline-block">
                <b-pagination
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    @page-click="pageSearch"
                    align="center"
                  ></b-pagination>
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="6"
            style="padding:0 5px 0 0; border-left:1px solid lightgrey;">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">접수 신청 이력</b-col>
              </b-row>
              <hr />
              
              <b-row
                class="mt-1">
                <b-col>
                  <b-modal 
                    v-model="modalShow" 
                    @ok="changPassword"
                    title="비밀번호 변경">
                    <b-form-input
                      type="password"
                      v-model="member.memberPassword"
                      placeholder="비밀번호를 입력하세요." />
                    <b-form-input
                      type="password"
                      v-model="member.passwordConfirm"
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
    fields: [ { key: 'memberId', label: 'No' },{ key: 'name', label: '위탁업체명' }, { key: 'email', label: '담당자' }, { key: 'mobile', label: '사업자번호' }, { key: 'role.roleDesc', label: '전화번호' } ],
    members: null,
    test: '1111',
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition:'',
    member: { id: null, name: null, email: null, mobile: null, memberId: null, memberPassword: null, passwordConfirm: null, role:{id: null}},
    roleList: null,
    modalShow: false
  }),
  beforeMount () {
      this.search()
      this.getRoleList()
  },
  methods: {
    companyClick: function (item) {
      this.member.id = item.id
      this.member.memberId = item.memberId
      this.member.name = item.name
      this.member.email = item.email
      this.member.mobile = item.mobile
      if(item.role){
        this.member.role.id = item.role.id||null
      }else{
        this.member.role.id = null
      }
    },
    cancel: function (){
      this.member = { id: null, name: null, email: null, mobile: null, memberId: null, memberPassword: null, passwordConfirm: null, role:{id: null}}
    },
    register: function (){
      if(confirm("회원을 등록하시겠습니까?")){
      this.$http.post(`/api/member`,this.member)
      .then(response => {
        if(response.data.result === 'success'){
          alert("회원가입 되었습니다.")
          this.search(this.currentPage);
        }
        })
      }
       
    },
    memberModify () {
      const _this = this
      if (this.member.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }
      if (this.member.memberId == null) {
        alert('아이디를 입력해주세요.')
        return false
      }
      this.$http.put(`/api/member`,this.member)
      .then(response => {
        if(response.data.result === 'success'){
          alert("회원정보가 변경되었습니다.")
          this.search(this.currentPage);
        }
        })
    },
    search(num){
      const page = num-1|0;
      this.$http.get(`/api/member?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.members = response.data.content;
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number+1
        })
    },
    pageSearch(bvEvent, page){
      this.search(page);
    },
    async getRoleList(){
      await this.$http.get(`/api/role`)
      .then(response => {
        this.roleList = response.data
        })
    },
    passwordCheck(){
      if(this.member.memberPassword != this.member.passwordConfirm){
        alert('비밀번호가 일치하지 않습니다.')
        return false;
      }else if(this.member.memberPassword.length <= 10){
        alert('비밀번호는 10자리 이상이어야합니다.')
        return false;
      }
      
      return true;
    },
    changPassword(bvModalEvt){
      if(!this.passwordCheck()){
        bvModalEvt.preventDefault()
        return false;
      }
      this.$http.put(`/api/member/changePassword`,this.member)
      .then(response => {
        if(response.data.result === 'success'){
          alert("비밀번호가 변경되었습니다.")
          this.search(this.currentPage);
        }else{
          bvModalEvt.preventDefault()
        }
        })
    },
    memeberDelete(){
      if(confirm("정말 회원을 삭제하시겠습니까?")){
      this.$http.delete(`/api/member`,{data:this.member})
      .then(response => {
        if(response.data.result === 'success'){
          alert("회원이 삭제되었습니다.")
          this.search(this.currentPage);
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
