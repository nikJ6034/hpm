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

                <b-table-simple
                  small
                  responsive
                  bordered
                  class="tbl-mgb10">
                  <colgroup>
                    <col style="width:10%;">
                    <col style="width:10%;">
                    <col style="width:10%;">
                    <col style="width:*">
                  </colgroup>
                  <b-tbody>
                    <b-tr class="trBgW">
                      <b-th
                        rowspan="2"
                        class="text-center">성적서 발행<br>업체명/주소</b-th>
                      <b-td
                        rowspan="2"
                        class="text-center" >
                        <b-form-group v-slot="{ ariaDescribedby }">
                          <b-form-radio-group
                            v-model="application.customerSameYn"
                            :options="options"
                            :aria-describedby="ariaDescribedby"
                            name="radios-stacked"
                            stacked
                            :disabled="true"
                            @change="customerSameChange"
                          />
                        </b-form-group>
                      </b-td>
                      <b-th>업체명</b-th>
                      <b-td>
                        {{ application.requestCustomerName }}
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>주소</b-th>
                      <b-td>
                        {{ application.requestCustomerAddress }}
                      </b-td>
                    </b-tr>
                  </b-tbody>
                </b-table-simple>

                <b-table-simple
                  small
                  bordered
                  class="tbl-mgb10">
                  <colgroup>
                    <col style="width:15%;">
                    <col style="width:35%;">
                    <col style="width:15%;">
                    <col style="width:25%;">
                    <col style="width:10%;">
                  </colgroup>
                  <b-tbody>
                    <b-tr class="trBgW">
                      <b-th colspan="2">
                        ★ 현장교정 :
                        <b-form-checkbox
                          id="checkbox-1"
                          v-model="application.fieldCorrectionNeedYn"
                          name="checkbox-1"
                          value="Y"
                          unchecked-value="N"
                          :disabled="true"
                          inline
                          class="ml-5"
                        >
                          필요
                        </b-form-checkbox>
                      </b-th>
                      <b-th colspan="3">
                        ★ 필증상에 권장교정일 주기 기입여부 :
                        <b-form-checkbox
                          id="checkbox-2"
                          v-model="application.recCalibrationDayYn"
                          name="checkbox-2"
                          value="Y"
                          unchecked-value="N"
                          inline
                          :disabled="true"
                          class="ml-5"
                        >
                          필요
                        </b-form-checkbox>
                      </b-th>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th rowspan="2">기준일타입</b-th>
                      <b-td
                        rowspan="2"
                        class="text-center">
                        <b-form-radio-group
                          v-model="application.appliRegDateType"
                          :options="options2"
                          :disabled="true"
                          name="radios-stacked2"
                          class="fl mr-1"
                        />
                        {{ application.appliRegDate }}
                      </b-td>
                      <b-th>신청인</b-th>
                      <b-td>
                        {{ application.applicant }}
                      </b-td>
                      <b-td rowspan="2">
                        <b-img
                          :src="uploadFileImg"
                          width="100px;"
                          height="100px;"
                          alt="서명 이미지" />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>이메일</b-th>
                      <b-td>
                        {{ application.applicantEmail }}
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>인수방법</b-th>
                      <b-td colspan="4">
                        <b-form-radio-group
                          v-model="application.takeOverType"
                          :options="options3"
                          :disabled="true"
                          name="radios-stacked3"
                          class="fl mr-1"
                        />
                      </b-td>
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

                  <b-row class="mt-2">
                    <b-col>
                      <b-button
                        class="float-right mr-1"
                        variant="outline-danger"
                        size="sm"
                        @click="remove"
                      >삭제</b-button>

                      <b-button
                        class="float-right mr-1"
                        variant="outline-primary"
                        size="sm"
                        @click="register"
                      >{{ saveButtonName }}</b-button>

                      <b-button
                        class="float-right mr-1"
                        variant="outline-primary"
                        size="sm"
                      >신청서 보기</b-button>
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
    if (customerId) {
      this.getCustomer(customerId)
    }

    const id = this.$route.query.id
    if (id) {
      this.getApplication(id)
    }
    this.uploadFileImg = 'http://placehold.it/150x150'
  },
  methods: {
    getApplication: function (id) {
      this.$http.get(`/api/application/${id}`)
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
    },
    getCustomer: function (customerId) {
      this.$http.get(`/api/customer/${customerId}`)
      .then(response => {
        this.application.customer = response.data
        })
    },
    goApplicationWritePage: function () {
      if (this.company.id == null) {
        alert('사업자를 선택해주세요.')
        return false
      } else {
        this.$router.push(`/application/write?companyId=${this.company.id}`)
      }
    },
    fnChangeImage: function (e) {
      const file = e.target.files[0] // Get first index in files
      this.uploadFileImg = URL.createObjectURL(file) // Create File URL
    },
    customerSameChange: function (val) {
      if (val === 'Y') {
        this.application.requestCustomerName = null
        this.application.requestCustomerAddress = null
      }
    },
    register: function () {
      this.$router.push(`/dashboard/application/write?id=${this.application.id}`)
    },
    remove: function () {
      if (confirm('삭제후에는 복구가 불가능합니다. 삭제하시겠습니까?')) {
      this.$http.delete(`/api/application`, { data: this.application })
      .then(response => {
        if (response.data.result === 'success') {
          alert('삭제되었습니다.')
          this.$router.push(`/dashboard/application`)
        }
      })
      }
    },
    signImageUrl: function () {
      if(this.application.customerSignImg){
        this.$http.get(`/api/img/file/${this.application.customerSignImg.id}`, { responseType: 'blob' }).then(response => {
          const url = window.URL.createObjectURL(new Blob([response.data]))
              this.uploadFileImg = url
        }).catch(err => console.log(err))
      }
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
