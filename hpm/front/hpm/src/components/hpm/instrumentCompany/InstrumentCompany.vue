<template>
  <v-container
    fill-height
    fluid
    grid-list-xl>
    <v-layout
      justify-center
      wrap>
      <b-container
        fluid>
        <b-row
          class="text-center"
          style="padding: 0 0 0 10px;">
          <b-col
            xl="5"
            style="padding:0 5px 0 5px">
            <b-container
              style="height: 750px;">
              <b-row>
                  <b-col class="f25">위탁업체 목록</b-col>
              </b-row>
              <hr/>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select class="mb-3">
                    <b-form-select-option
                      :value="null"
                      disabled>선택해주세요.</b-form-select-option>
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
                    variant="outline-primary">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="instrumentCompanis"
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
            xl="7"
            style="padding:0 5px 0 0; border-left:1px solid lightgrey;">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col v-if="instrumentCompany.id == null" class="f25">위탁업체 등록</b-col>
                <b-col v-else class="f25">위탁업체 정보</b-col>
              </b-row>
              <hr/>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  위탁업체명
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    v-model="instrumentCompany.name"
                    placeholder="위탁업체명을 입력하세요." />
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
                    v-model="instrumentCompany.repName"
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
                    v-model="instrumentCompany.companyRegNumber"
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
                    v-model="instrumentCompany.item"
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
                    v-model="instrumentCompany.tel"
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
                    v-model="instrumentCompany.fax"
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
                    v-model="instrumentCompany.picName"
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
                    v-model="instrumentCompany.postNumber"
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
                  cols="2"
                  class="mFormLbl text-right">
                </b-col>
                <b-col cols="10">
                  <b-form-input readonly=readonly
                    v-model="instrumentCompany.adress"
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
                    v-model="instrumentCompany.adressDetail"
                    placeholder="상세주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                   E-MAIL
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
                  class="mFormLbl text-right">
                   기타
                </b-col>
                <b-col cols="10">
                  <b-form-textarea />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col>
                  <b-button
                    v-if="instrumentCompany.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="cancel">취소</b-button>
                  <b-button
                    v-if="instrumentCompany.id == null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    >등록</b-button>
                  <b-button
                    v-if="instrumentCompany.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="companyModify">수정</b-button>
                    <b-button
                    v-if="instrumentCompany.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    >삭제</b-button>  
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
import instrumentCompanis from '@/testdata/instrumentCompany'

export default {
  data: () => ({
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'address', label: '새주소' }, { key: 'number', label: '사업자 번호' } ],
    instrumentCompanis,
    test: '1111',
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition:'',
    instrumentCompany: { id: null, name: null, postCode: null, address: null, address2: null, number: null }
  }),
  methods: {
    companyClick: function (item) {
      this.instrumentCompany.id = item.id
      this.instrumentCompany.name = item.name
      this.instrumentCompany.postCode = item.postCode
      this.instrumentCompany.address = item.address
      this.instrumentCompany.address2 = item.address2
      this.instrumentCompany.number = item.number
    },
    companyModify () {
      const _this = this
      if (this.instrumentCompany.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (this.instrumentCompany.id == null) {
        this.instrumentCompany.push(this.instrumentCompany)
      } else {
        this.instrumentCompanis.some(function (item) {
          if (item.id === _this.instrumentCompany.id) {
            item.name = _this.instrumentCompany.name
            item.postCode = _this.instrumentCompany.postCode
            item.address = _this.instrumentCompany.address
            item.address2 = _this.instrumentCompany.address2
            item.number = _this.instrumentCompany.number
          }
        })
      }
    },
    cancel: function (){
      this.instrumentCompany = { id: null, name: null, postCode: null, address: null, address2: null, number: null}
    },
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
