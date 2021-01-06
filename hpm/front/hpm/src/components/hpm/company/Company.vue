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
                    class="mb-3">
                    <b-form-select-option value="">선택해주세요.</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    type="text"
                    placeholder="검색조건을 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    variant="outline-primary"
                    @click="test2">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="companis"
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
                <b-col v-if="company.id == null" class="f25">거래처 등록</b-col>
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
                    v-model="company.name"
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
                    v-model="company.repName"
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
                    v-model="company.companyRegNumber"
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
                  ></b-form-select>
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
                    v-model="company.item"
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
                    v-model="company.tel"
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
                    v-model="company.fax"
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
                    v-model="company.picName"
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
                    v-model="company.postNumber"
                    readonly=readonly
                    placeholder="우편번호" />
                   <b-button
                    class="float-left mr-1"
                    variant="outline-primary"
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
                    v-model="company.adress"
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
                    v-model="company.adressDetail"
                    placeholder="상세주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">E-MAIL
                </b-col>
                <b-col cols="10">
                  <b-form-input
                    placeholder="Email를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">기타
                </b-col>
                <b-col cols="10">
                  <b-form-textarea />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col>
                  <b-button
                    v-if="company.id == null"
                    class="float-right"
                    variant="outline-primary"
                    >등록</b-button>

                  <b-button
                    v-if="company.id != null"
                    class="float-right"
                    variant="outline-primary"
                    @click="cancel">취소</b-button>
                  <b-button
                    v-if="company.id != null"
                    class="float-right mr-3"
                    variant="outline-danger"
                    >삭제</b-button>
                  <b-button
                    v-if="company.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="companyModify">수정</b-button>
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
import companis from '@/testdata/company'

export default {
  data: () => ({
    fields: [ { key: 'id', label: 'No.'} ,{key: 'name', label: '거래처명' }, { key: 'number', label: '사업자번호' }, { key: 'address', label: '담당자' } , { key: 'address2', label: '전화번호' }],
    companis,
    test: '1111',
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition:'',
    company: { id: null, name: null, postCode: null, address: null, address2: null, number: null }
  }),
  beforeMount () {
        
  },
  methods: {
    companyClick: function (item) {
      this.company.id = item.id
      this.company.name = item.name
      this.company.postCode = item.postCode
      this.company.address = item.address
      this.company.address2 = item.address2
      this.company.number = item.number
    },
    companyModify () {
      const _this = this
      if (this.company.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (this.company.id == null) {
        this.companis.push(this.company)
      } else {
        this.companis.some(function (item) {
          if (item.id === _this.company.id) {
            item.name = _this.company.name
            item.postCode = _this.company.postCode
            item.address = _this.company.address
            item.address2 = _this.company.address2
            item.number = _this.company.number
          }
        })
      }
    },
    test2: function () {
      this.$http.get("/api/members")
      .then(response => {
          console.log(response)
        })
    },
     cancel: function (){
      this.company = { id: null, name: null, postCode: null, address: null, address2: null, number: null}
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
