<template>
    <div>
      <base-header type="gradient-success" class="pb-6 pt-1">
      </base-header>
      <b-container
        class="my-2"
        fluid>
        <b-row
          class="text-center"
          style="padding: 0px 10px 10px 10px;">
          <b-col
            style="padding:0 5px 0 5px">
            <b-container
              fluid>
              <b-row>
                <b-col class="f25">신청서 상세정보</b-col>
              </b-row>
              <hr>
              <b-row>
                <b-container
                  fluid
                  class="appContent mb-2">
                  <b-row>
                    <b-col class="appCol font-weight-bold" style="font-size: 32px;">신청서</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">회사명</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.customer.name }}</b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="1">대표자</b-col>
                    <b-col class="appCol text-left" cols="8" md="5">{{ application.customer.repName }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">사업자등록번호</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.customer.companyRegNumber }}</b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="1">업태</b-col>
                    <b-col class="appCol text-left" cols="8" md="2">{{ application.customer.bizCondition }}</b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="1">종목</b-col>
                    <b-col class="appCol text-left" cols="8" md="2">{{ application.customer.item }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">주소</b-col>
                    <b-col class="appCol text-left" cols="8" md="10">{{ application.customer.adress }} {{ application.customer.adressDetail }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">전화번호</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.customer.tel }}</b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="1">팩스번호</b-col>
                    <b-col class="appCol text-left" cols="8" md="5">{{ application.customer.fax }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">성적서 발행 업체명</b-col>
                    <b-col class="appCol text-left" cols="8" md="6">{{ application.requestCustomer&&application.requestCustomer.name }}</b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">신청자 동일여부</b-col>
                    <b-col class="appCol" cols="8" md="2">
                      <b-form-group v-slot="{ ariaDescribedby }" style="margin-bottom: 0px">
                        <b-form-radio-group
                          v-model="application.customerSameYn"
                          :options="options"
                          :aria-describedby="ariaDescribedby"
                          name="radios-stacked"
                          :disabled="true"
                          @change="customerSameChange"
                        />
                      </b-form-group>
                    </b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">주소</b-col>
                    <b-col class="appCol text-left" cols="8" md="10">{{ application.requestCustomer&&application.requestCustomer.adress }} {{ application.requestCustomer&&application.requestCustomer.adressDetail }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol" cols="12" md="6">
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
                    </b-col>
                    <b-col class="appCol" cols="12" md="6">
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
                    </b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">접수형식</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">
                      <b-form-radio-group
                          v-model="application.appliRegDateType"
                          :options="options2"
                          :disabled="true"
                          name="radios-stacked2"
                          class="fl mr-1"
                        />
                    </b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">접수날짜</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.appliRegDate }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col style="line-height: 70px;" class="appCol font-weight-bold" cols="4" md="2">신청인</b-col>
                    <b-col  class="appCol text-left" cols="8" md="10">
                      <b-row>
                        <b-col cols="8" class="my-auto">
                          {{ application.applicant }}
                        </b-col>
                        <b-col cols="4" class="my-auto">
                          <b-img
                          :src="uploadFileImg"
                          style="width:70px"
                          class="float-right"
                          alt="서명 이미지" />
                        </b-col>
                      </b-row>
                    </b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">신청인 전화번호</b-col>
                    <b-col class="appCol text-left" cols="8" md="10">{{ application.applicantTel }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">이메일</b-col>
                    <b-col class="appCol text-left" cols="8" md="10">{{ application.applicantEmail }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">인수방법</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">
                      <b-form-radio-group
                          v-model="application.takeOverType"
                          :options="options3"
                          :disabled="true"
                          name="radios-stacked3"
                          class="fl mr-1"
                        />
                    </b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">인수정보</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.deliveryInfo }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">성적서 작성 언어</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">
                      <template v-if="application.reportLanguage">
                        {{ application.reportLanguageNm }}
                      </template>
                    </b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">기술책임자 검토</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">{{ application.technicalManager }}</b-col>
                  </b-row>
                  <b-row>
                    <b-col class="appCol font-weight-bold" cols="4" md="2">접수자 이름</b-col>
                    <b-col class="appCol text-left" cols="8" md="4">
                        {{ application.regNm }}
                    </b-col>
                    <b-col class="appCol font-weight-bold" cols="4" md="2"></b-col>
                    <b-col class="appCol text-left" cols="8" md="4"></b-col>
                  </b-row>
                </b-container>
              </b-row>
              <b-row>
                <b-container
                  fluid>
                  <b-row class="mt-1">
                    <b-col style="padding-right: 0px; padding-left: 0px;">
                        <grid
                          v-if="application.applicationLogList.length > 0"
                          ref="tuiGrid"
                          :columns="columns"
                          :options="gridOptions"
                          :columnOptions="columnOptions"
                          :data="application.applicationLogList"
                          style="height:400px"
                          language="ko"
                        />
                    </b-col>
                  </b-row>

                  <b-row class="mt-2">
                    <b-col>
                      <b-button
                        class="float-right mr-1"
                        variant="outline-primary"
                        size="sm"
                        @click="goList"
                      >목록</b-button>
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
                        @click="appExcelDown()"
                      >신청서 보기</b-button>

                      <b-button
                        class="float-right mr-2"
                        variant="outline-primary"
                        size="sm"
                        @click="dataDownload">납품</b-button>
                      <!-- <b-button
                        class="float-right mr-1"
                        variant="outline-primary"
                        size="sm"
                        @click="dataDownload()"
                      >견적 완료 납품작성</b-button> -->
                    </b-col>
                  </b-row>
                </b-container>
              </b-row>
            </b-container>
          </b-col>
        </b-row>
      </b-container >
	</div>
</template>

<script>

import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  components: {
    Grid
  },
  data: () => ({
    gridOptions: { bodyHeight: 'fitToParent', rowHeaders: ['rowNum']},
    columnOptions: { resizable: true },
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
      takeOverType: {key:'', value:''},
      regMember: { id: 0 },
      regDate: '',
      customerSignImgFile: [],
      applicationLogList: [],
      deliveryInfo: ''
    },
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
      { text: '반출', value: 'EXPORT' },
      { text: '현장+반출', value: 'SPOTEXPORT' }
    ],
    options3: [
      { text: '직접전달', value: 'DIRECTLY' },
      { text: '고객방문', value: 'VISIT' },
      { text: '택배', value: 'DELIVERY' }
    ],
    columns: [
        { header: '접수번호', name: 'regNumber', width: 100, align:'center' },
        { header: '성적서 번호', name: 'reportNumber', width: 100, align:'center' },
        { header: '장비명', name: 'deviceName', width: 200, align:'center' },
        { header: '제작회사', name: 'productionCompany', width: 100, align:'center' },
        { header: '모델', name: 'model', width: 100, align:'center' },
        { header: '규격/RANGE', name: 'standard', width: 100, align:'center' },
        { header: '분해능', name: 'resolution', width: 100, align:'center' },
        { header: '기기번호', name: 'deviceNumber', width: 100, align:'center' },
        { header: '수량', name: 'quantity', width: 100, align:'center' },
        { header: '비고', name: 'etc', width: 200, align:'center' },
        { header: '위탁기관', name: 'consignmentCompanyNm', width: 100, align:'center' },
        { header: '교정/시험/자체', name: 'inspectionTypeNm', width: 100, align:'center' },
        { header: '출장/반출/입고', name: 'carryTypeNm', width: 100, align:'center' },
        { header: '교정료', name: 'correctionFee', width: 100, align:'center' }
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
          this.signImageUrl()
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
    },
    appExcelDown: function () {
      this.$http.get(`/api/excel/application/${this.application.id}`, { responseType: 'blob' }).then(response => {
          const blob = new Blob(
					[response.data], { type: 'application/vnd.ms-excel.sheet.macroEnabled.12;charset=utf-8' })
                    const aEle = document.createElement('a');     // Create a label
                    const href = window.URL.createObjectURL(blob);       // Create downloaded link
                    aEle.href = href
                    
                    //aEle.download = this.application.applicationLogList[0].consignmentCompanyNm+" "+this.application.customer.name+" "+this.$moment().format("YYYY년 MM월 DD일")+"["+this.application.appliRegDateTypeNm+"]";  // File name after download
                    aEle.download = "교정신청서 2022";
                    document.body.appendChild(aEle);
                    aEle.click();     // Click to download
                    document.body.removeChild(aEle); // Download complete remove element
                    window.URL.revokeObjectURL(href) // Release blob object
          
        }).catch(err => console.log(err))
    },
    dataDownload(){
      const logList = [];
      const _this = this;
      this.application.applicationLogList.forEach(function(item){
        item.customerName = _this.application.customer.name
        item.customerTel = _this.application.customer.tel
        item.customerFax = _this.application.customer.fax
        item.customerPicName = _this.application.customer.picName
        item.customerAddress = _this.application.customer.adress
        item.customerAddressDetail = _this.application.customer.adressDetail
        logList.push(item)
      })

      this.$http.post(`/api/applicationlog/excel`, logList ,{ responseType: 'blob' }).then(response => {
          const blob = new Blob(
					[response.data], { type: 'application/vnd.ms-excel.sheet.macroEnabled.12;charset=utf-8' })
                    const aEle = document.createElement('a');     // Create a label
                    const href = window.URL.createObjectURL(blob);       // Create downloaded link
                    aEle.href = href;
                    aEle.download = '교정견적서 작성 2022'//'업회원 '+this.$moment().format("YYYY년 MM월 DD일")+' '+this.application.customer.name;  // File name after download
                    document.body.appendChild(aEle);
                    aEle.click();     // Click to download
                    document.body.removeChild(aEle); // Download complete remove element
                    window.URL.revokeObjectURL(href) // Release blob object
        }).catch(err => console.log(err))
    },
    goList: function(){
      this.$router.push(`/dashboard/application`)
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
  /* .appContent {background-color: white; border: solid 1px #dee2e6;} */
  .appCol {border: solid 1px #dee2e6;}
</style>
