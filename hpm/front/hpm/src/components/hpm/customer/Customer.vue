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
                  <b-col class="f25">거래처 목록</b-col>
              </b-row>
              <hr/>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    v-model="condition"
                    class="mb-3">
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="name">거래처 이름</b-form-select-option>
                    <b-form-select-option value="tel">전화번호</b-form-select-option>
                    <b-form-select-option value="companyRegNumber">사업자번호</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="customerName"
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
                    @click="search()">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="customers"
                  :fields="fields"
                  @row-clicked="itemClick" 
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
                <b-col v-if="customer.id == null" class="f25">거래처 등록</b-col>
                <b-col v-else class="f25">거래처 정보</b-col>
              </b-row>
              <hr/>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  거래처명
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    v-model="customer.name"
                    placeholder="거래처명을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  대표자명
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    v-model="customer.repName"
                    placeholder="대표자명을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  사업자번호
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    v-model="customer.companyRegNumber"
                    placeholder="사업자번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   업태
                </b-col>
                <b-col cols="10">
                  <b-form-select 
                    :options="codeList"
                    v-model="customer.bizCondition"
                    value-field="code"
                    text-field="codeName"
                  >
                  </b-form-select>
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   종목
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    v-model="customer.item"
                    placeholder="종목을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   전화번호
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    v-model="customer.tel"
                    placeholder="전화번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   팩스번호
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    v-model="customer.fax"
                    placeholder="팩스번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   담당자
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    v-model="customer.picName"
                    placeholder="담당자를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  주소
                </b-col>
                <b-col cols="10">
                  <b-form-input style="width:150px;"
                    class="float-left mr-1"
                    readonly=readonly
                    v-model="customer.postNumber"
                    placeholder="우편번호" />
                   <b-button
                    class="float-left mr-1"
                    variant="outline-primary"
                    @click="postPopupOpen"
                    >주소검색</b-button>
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2">
                </b-col>
                <b-col cols="10">
                  <b-form-input readonly=readonly
                    v-model="customer.adress"
                    placeholder="주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2">
                </b-col>
                <b-col cols="10">
                  <b-form-input 
                    v-model="customer.adressDetail"
                    placeholder="상세주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">기타
                </b-col>
                <b-col cols="10">
                  <b-form-textarea 
                    v-model="customer.etc" />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col>
                  <b-button
                    v-if="customer.id == null"
                    class="float-right"
                    variant="outline-primary"
                    @click="register"
                    >등록</b-button>

                  <b-button
                    v-if="customer.id != null"
                    class="float-right"
                    variant="outline-primary"
                    @click="cancel">취소</b-button>
                  <b-button
                    v-if="customer.id != null"
                    class="float-right mr-3"
                    variant="outline-danger"
                    @click="remove"
                    >삭제</b-button>
                  <b-button
                    v-if="customer.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="modify">수정</b-button>
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
    fields: [ { key: 'id', label: 'No.'} ,{key: 'name', label: '거래처명' }, { key: 'companyRegNumber', label: '사업자번호' }, { key: 'picName', label: '담당자' } , { key: 'tel', label: '전화번호' }],
    customers: [],
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition:'',
    customer: { id: null, name: null, repName: null, companyRegNumber: null, bizCondition: '', item: null, tel: null, fax: null, picName: null, postNumber: null, adress: null, adressDetail: null, etc: null},
    codeList : []
  }),
  beforeMount () {
    this.search()
    this.codeSearch()
  },
  methods: {
    search(num){
      const page = num-1|0;
      this.$http.get(`/api/customer?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.customers = response.data.content;
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number+1
        })
    },
    pageSearch(bvEvent, page){
      this.search(page);
    },
    codeSearch(){
      this.$http.get(`/api/code/code/bizCondition`)
      .then(response => {
        this.codeList = [...[{code:'',codeName:'선택해주세요.'}],...response.data.codeList];
        })
    },
    itemClick: function (item) {
      this.customer = JSON.parse(JSON.stringify(item))
    },
    register: function (){
      if(confirm("거래처를 등록하시겠습니까?")){
      this.$http.post(`/api/customer`,this.customer)
      .then(response => {
        if(response.data.result === 'success'){
          alert("거래처가 등록되었습니다.")
          this.search(this.currentPage);
        }
        })
      }
       
    },
    modify: function () {
      if (this.customer.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }
      if(confirm("거래처 정보를 수정하시겠습니까?")){
        this.$http.put(`/api/customer`,this.customer)
        .then(response => {
          if(response.data.result === 'success'){
            alert("거래처 정보가 변경되었습니다.")
            this.search(this.currentPage);
          }
          })
      }
      
    },
    remove(){
      if(confirm("정말 거래처를 삭제하시겠습니까?")){
      this.$http.delete(`/api/customer`,{data:this.customer})
      .then(response => {
        if(response.data.result === 'success'){
          alert("거래처가 삭제되었습니다.")
          this.search(this.currentPage);
          this.cancel()
        }
        })
      }
    },
     cancel: function (){
      this.customer = { id: null, name: null, repName: null, companyRegNumber: null, bizCondition: null, item: null, tel: null, fax: null, picName: null, postNumber: null, adress: null, adressDetail: null, etc: null}
    },
    postPopupOpen: function (){
      const _this = this;
      new daum.Postcode({
          oncomplete: function(data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
              // 예제를 참고하여 다양한 활용법을 확인해 보세요.
              _this.customer.postNumber = data.zonecode
              _this.customer.adress = data.jibunAddress
          }
      }).open();
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
