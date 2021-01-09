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
                  <b-col class="f25">권한 목록</b-col>
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
                <b-col v-if="company.id == null" class="f25">권한 등록</b-col>
                <b-col v-else class="f25">권한 정보</b-col>
              </b-row>
              <hr/>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  권한
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    v-model="company.name"
                    placeholder="권한을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  권한명
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    v-model="company.repName"
                    placeholder="권한명을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">설명
                </b-col>
                <b-col cols="10">
                  <b-form-textarea
                  rows="5"
                   />
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
    fields: [ { key: 'id', label: 'No.'} ,{key: 'name', label: '권한' }, { key: 'number', label: '권한명' }],
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
    },
    pageSearch(bvEvent, page){
      this.search(page);
    },
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
