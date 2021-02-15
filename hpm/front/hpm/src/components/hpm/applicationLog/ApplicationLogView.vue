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
          style="padding: 0px 10px 10px 10px;">
          <b-col
            style="padding:0 5px 0 5px">
            <b-container
              fluid>
              <b-row>
                <b-col class="f25">접수대장 이력</b-col>
              </b-row>
              <b-row>
                <b-container
                  fluid>
                  <b-row class="mt-1">
                    <b-col
                      cols="6"
                      class="pr-0">
                      <b-form inline>
                        <b-form-select
                          v-model="condition"
                          class="mr-2">
                          <b-form-select-option value="">전체</b-form-select-option>
                          <b-form-select-option value="customerName">거래처명</b-form-select-option>
                          <b-form-select-option value="2">접수번호</b-form-select-option>
                          <b-form-select-option value="deviceName">장비명</b-form-select-option>
                          <b-form-select-option value="productionCompany">제조사</b-form-select-option>
                          <b-form-select-option value="3">모델</b-form-select-option>
                          <b-form-select-option value="deviceNumber">기기번호</b-form-select-option>
                        </b-form-select>

                        <b-form-input
                          id="customerName"
                          v-model="keyword"
                          type="text"
                          class="mr-2 col-7"
                          placeholder="키워드를 입력해주세요." />

                        <b-button
                          variant="outline-primary"
                          @click="getApplication">조회</b-button>
                      </b-form>
                    </b-col>
                  </b-row>
                  <b-row class="mt-1">
                    <b-col>
                      <b-table fixed sticky-header="600px" responsive :items="applicationLogList" :fields="fields"></b-table>
                    </b-col>
                  </b-row>
                </b-container>
              </b-row>
            </b-container>
          </b-col>
        </b-row>
      </b-container >
    </v-layout>
  </v-container>
</template>

<script>

import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  components: {
    Grid
  },
  data: () => ({
    applicationLogList: [],
    fields: [
              {key: 'regDate', label: '접수일', thStyle: { width: '150px'} },
              {key: 'requestCustomerName', label: '대행 업체', thStyle: { width: '200px'} },
              {key: 'customerName', label: '거래처', thStyle: { width: '200px'} },
              {key: 'customerPicName', label: '담당자', thStyle: { width: '150px'} },
              {key: 'customerTel', label: '회사전화번호', thStyle: { width: '150px'} },
              {key: 'customerMobile', label: '연락처', thStyle: { width: '150px'} },
              {key: 'customerEmail', label: '이메일주소', thStyle: { width: '200px'} },
              {key: 'inspectionType', label: '검수타입', thStyle: { width: '100px'} },
              {key: 'consignmentCompanyName', label: '위탁기관', thStyle: { width: '100px'} },
              {key: 'regNumber', label: '접수번호', thStyle: { width: '100px'} },
              {key: 'deviceName', label: '장비명', thStyle: { width: '200px'} },
              {key: 'deviceNumber', label: '기기번호', thStyle: { width: '150px'} },
              {key: 'productionCompany', label: '제조사', thStyle: { width: '150px'} },
              {key: 'model', label: '모델', thStyle: { width: '150px'} },
              {key: 'standard', label: '규격', thStyle: { width: '150px'} },
              {key: 'resolution', label: '분해능', thStyle: { width: '150px'} },
              {key: 'carryType', label: '출장/반출/입고', thStyle: { width: '200px'} },
              {key: 'correctionFee', label: '교정료', thStyle: { width: '150px'} },
              {key: 'quantity', label: '수량', thStyle: { width: '100px'} },
              {key: 'reportNumber', label: '성적서 번호', thStyle: { width: '150px'} },
              {key: 'unit', label: '단위', thStyle: { width: '100px'} },
              {key: 'correctionDate', label: '교정일자', thStyle: { width: '150px'} },
              {key: 'place', label: '장소', thStyle: { width: '150px'} },
              {key: 'practitioner', label: '실무자', thStyle: { width: '150px'} },
              {key: 'middleCategory', label: '중분류', thStyle: { width: '150px'} },
              {key: 'smallCategory', label: '소분류', thStyle: { width: '150px'} },
              {key: 'publishedDate', label: '발행일자', thStyle: { width: '150px'} },
              {key: 'technicalManager', label: '기술책임자', thStyle: { width: '150px'} },
              {key: 'reportLanguage.name', label: '성적서 언어', thStyle: { width: '200px'} },
              {key: 'etc', label: '비고', thStyle: { width: '250px'} }

            ],
    condition: "",
    keyword: ""
  }),
  computed: {
   
  },
  async created () {
    
  },
  beforeMount () {
    this.getApplication()
  },
  methods: {
    getApplication: function () {
      this.$http.get(`/api/applicationAll?condition=${this.condition}&keyword=${this.keyword}`)
      .then(response => {
        if (response) {
          this.applicationLogList = response.data
          // this.application.applicationLogList.forEach(function (item) {
          //   item.consignmentCompany = item.consignmentCompany.name
          //   if(item.reportLanguage){
          //     item.reportLanguage = item.reportLanguage.name
          //   }
          //   logList.push(item)
          // })
        }
        })
    }
  }
}
</script>

<style>
  .pointer {cursor: pointer;}
  .f25 {font-size: 25px; text-align: left; font-weight: bold;}
  .trBgW { background-color:white !important;}
  .trBgW th,.trBgW td { vertical-align: middle !important; }
  .fl { float: left;}
  .y-scroll { height: 700px; overflow-y: scroll;}
  .tbl-mgb10 { margin-bottom:10px !important; }
</style>
