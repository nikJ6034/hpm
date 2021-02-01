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
                <b-col class="f25">신청서 목록</b-col>
              </b-row>

              <hr>

              <b-row>
                <b-table-simple
                  small
                  responsive
                  bordered
                  class="tbl-mgb10">
                  <colgroup>
                    <col style="width:*">
                    <col style="width:10%;">
                    <col style="width:20%;">
                    <col style="width:10%;">
                    <col style="width:20%;">
                    <col style="width:10%;">
                    <col style="width:20%;">
                  </colgroup>
                  <b-tbody>
                    <b-tr class="trBgW">
                      <b-th rowspan="4"><font size="5">* 신청인</font></b-th>
                      <b-th class="text-center">회사명</b-th>
                      <b-td class="text-left">{{ application.customer.name }}</b-td>
                      <b-th class="text-center">대표자</b-th>
                      <b-td
                        class="text-left"
                        colspan="3">{{ application.customer.repName }}</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">사업자등록번호</b-th>
                      <b-td class="text-left">{{ application.customer.companyRegNumber }}</b-td>
                      <b-th class="text-center">업태</b-th>
                      <b-td class="text-left">{{ application.customer.bizCondition }}</b-td>
                      <b-th class="text-center">종목</b-th>
                      <b-td class="text-left">{{ application.customer.item }}</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">주소</b-th>
                      <b-td
                        class="text-left"
                        colspan="5">[{{ application.customer.postNumber }}] {{ application.customer.adress }} {{ application.customer.adressDetail }}</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">전화번호</b-th>
                      <b-td class="text-left">{{ application.customer.tel }}</b-td>
                      <b-th class="text-center">팩스번호</b-th>
                      <b-td
                        class="text-left"
                        colspan="3">{{ application.customer.fax }}</b-td>
                    </b-tr>
                  </b-tbody>
                </b-table-simple>
                <b-container
                  fluid>
                  <b-row class="mt-1">
                    <b-col>
                      <grid
                        ref="tuiGrid"
                        :columns="columns"
                        :options="gridOptions"
                        style="height:400px"
                        language="ko"
                      />
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
    gridOptions: { bodyHeight: 'fitToParent'},
    companis: [],
    uploadFileImg: null,
    application: {
      id: null,
      customer: { id: 0 },
      requestCustomerName: '',
      requestCustomerAddress: '',
      customerSameYn: 'Y',
      fieldCorrectionNeedYn: 'N',
      recCalibrationDayYn: 'N',
      appliRegDateType: '',
      appliRegDate: '',
      applicant: '',
      applicantEmail: '',
      consignee: '',
      takeOverDate: '',
      takeOverType: '',
      regMember: { id: 0 },
      regDate: '',
      customerSignImgFile: [],
      applicationLogList: []
    },
    deleteApplicationLogList: [],
    newLine: {
                name: null,
                instrumentName: null,
                quantity: 0
              },
    options: [
      { text: '상동', value: 'Y' },
      { text: '다름', value: 'N' }
    ],
    options2: [
      { text: '방문', value: 'VISIT' },
      { text: '택배', value: 'DELIVERY' },
      { text: '반출', value: 'EXPORT' }
    ],
    options3: [
      { text: '직접전달', value: 'DIRECTLY' },
      { text: '고객방문', value: 'VISIT' },
      { text: '택배', value: 'DELIVERY' }
    ]
  }),
  computed: {
    saveButtonName: function () {
      if (this.application.id) {
        return '수정'
      } else {
        return '접수'
      }
    }
  },
  async created () {
    this.columns = [
        { header: '사업자', name: 'consignmentCompany' },
        { header: '기기명', name: 'deviceName' },
        { header: '수량', name: 'quantity' },
        { header: '성적서 번호', name: 'reportNumber' },
        { header: '제작 회사', name: 'productionCompany' },
        { header: '기기번호', name: 'deviceNumber' },
        { header: '규격', name: 'standard' },
        { header: '단위', name: 'unit' },
        { header: '교정일자', name: 'correctionDate' },
        { header: '장소', name: 'place' },
        { header: '실무자', name: 'practitioner'},
        { header: '중분류', name: 'middleCategory'},
        { header: '소분류', name: 'smallCategory'},
        { header: '발행일자', name: 'publishedDate'},
        { header: '기술책임자', name: 'technicalManager'},
        { header: '성적서 언어', name: 'reportLanguage' }
    ]
  },
  beforeMount () {
    const customerId = this.$route.query.customerId
    const id = this.$route.query.id
    this.getApplication(id)
  },
  methods: {
    getApplication: function (id) {
      this.$http.get(`/api/applicationAll`)
      .then(response => {
        if (response) {
          this.application = response.data
          const logList = []
          this.application.applicationLogList.forEach(function (item) {
            item.consignmentCompany = item.consignmentCompany.name
            if(item.reportLanguage){
              item.reportLanguage = item.reportLanguage.name
            }
            logList.push(item)
          })
          this.signImageUrl()
          this.$refs.tuiGrid.invoke('resetData', logList)
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
