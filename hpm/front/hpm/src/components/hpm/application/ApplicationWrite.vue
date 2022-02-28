<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pt-1">
    </base-header>
    <b-container
      class="pt-6"
      fluid>
      <b-row
        class="text-center"
        style="padding: 0px 10px 10px 10px;">
        <b-col
          style="padding:0 5px 0 5px">
          <b-container
            fluid>
            <b-row>
              <b-col class="f25">신청서 등록/수정</b-col>
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
                  <b-col class="appCol text-left" cols="8" md="4">{{ application.customer.adress }} {{ application.customer.adressDetail }}</b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="1">기타정보</b-col>
                  <b-col class="appCol text-left" cols="8" md="5">
                    <b-button v-b-modal.modal-2 size="sm">기타정보</b-button>
                    <b-modal id="modal-2" size="lg" title="기타정보">
                      {{application.customer.etc}}
                    </b-modal>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">전화번호</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">{{ application.customer.tel }}</b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="1">팩스번호</b-col>
                  <b-col class="appCol text-left" cols="8" md="5">{{ application.customer.fax }}</b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">성적서 발행 업체명</b-col>
                  <b-col class="appCol text-left" cols="8" md="6">
                    {{application.requestCustomer&&application.requestCustomer.name}}
                    <b-button
                      v-if="application.customerSameYn == 'N'"
                      v-b-modal.modal-1
                      size="sm"
                      class="ml-2"
                      style="vertical-align:bottom; "
                      variant="outline-primary"
                    >업체검색</b-button>
                    <b-modal id="modal-1" size="lg" title="업체 검색" hide-footer>
                      <CustomerSearchBox @customer="setRequestCustomer"/>
                    </b-modal>
                  </b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">신청자 동일여부</b-col>
                  <b-col class="appCol" cols="8" md="2">
                    <b-form-group style="margin-bottom: 0px" v-slot="{ ariaDescribedby }">
                        <b-form-radio-group
                          v-model="application.customerSameYn"
                          :options="options"
                          :aria-describedby="ariaDescribedby"
                          name="radios-stacked"
                          @change="customerSameChange"
                        />
                      </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">주소</b-col>
                  <b-col class="appCol text-left" cols="8" md="10">
                    {{ application.requestCustomer&&application.requestCustomer.adress }} {{ application.requestCustomer&&application.requestCustomer.adressDetail }}
                  </b-col>
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
                        name="radios-stacked2"
                        size="sm"
                        class="fl mr-1"
                      />
                  </b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">접수날짜</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-input
                        id="example-input"
                        v-model="application.appliRegDate"
                        :readonly="true"
                        type="text"
                        placeholder="YYYY-MM-DD"
                        autocomplete="off"
                        size="sm"
                        class="fl"
                        style="width:120px;"
                      />
                      <b-form-datepicker
                        v-model="application.appliRegDate"
                        button-only
                        locale="ko-KR"
                        aria-controls="example-input"
                        size="sm"
                        class="fl"
                      />
                  </b-col>
                </b-row>
                <b-row>
                  <b-col style="line-height: 70px;" class="appCol font-weight-bold" cols="4" md="2">신청인</b-col>
                  <b-col class="appCol text-left" cols="6" md="8">
                    <b-form-input
                      v-model="application.applicant"
                      size="sm"
                      class="d-inline-block"
                      placeholder="신청인을 입력하세요." />
                    <b-form-file
                      id="file-small"
                      v-model="application.customerSignImgFile"
                      size="sm"
                      browse-text="신청인 서명 업로드"
                      placeholder=""
                      color="primary"
                      @change="fnChangeImage"/>
                  </b-col>
                  <b-col class="appCol pl-2" cols="2" md="2">
                    <b-img
                        :src="uploadFileImg"
                        width="60px;"
                        class="mt-1 ml-0"
                        alt="서명 이미지" />
                  </b-col>
                  
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2"><b-col class="my-auto">신청인 전화번호</b-col></b-col>
                  <b-col class="appCol text-left" cols="8" md="10">
                    <b-form-input
                      v-model="application.applicantTel"
                      size="sm"
                      placeholder="신청인 전화번호를 입력하세요." />
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2"><b-col class="my-auto">이메일</b-col></b-col>
                  <b-col class="appCol text-left" cols="8" md="10">
                    <b-form-input
                      v-model="application.applicantEmail"
                      size="sm"
                      placeholder="이메일를 입력하세요." />
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">인수방법</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-radio-group
                        v-model="application.takeOverType"
                        :options="options3"
                        name="radios-stacked3"
                        class="fl mr-1"
                      />
                  </b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">인수정보</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-input
                        v-model="application.deliveryInfo"
                        size="sm"
                        placeholder="" />
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">성적서 작성 언어</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-select v-model="application.reportLanguage" :options="languageOptions" size="sm" />
                  </b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">기술책임자 검토</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-input
                        v-model="application.technicalManager"
                        size="sm"
                        placeholder="" />
                  </b-col>
                </b-row>
                <b-row>
                  <b-col class="appCol font-weight-bold" cols="4" md="2">접수자 이름</b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    <b-form-input
                        v-model="application.regNm"
                        size="sm"
                        placeholder="" />
                  </b-col>
                  <b-col class="appCol font-weight-bold" cols="4" md="2"></b-col>
                  <b-col class="appCol text-left" cols="8" md="4">
                    
                  </b-col>
                </b-row>
              </b-container>
            </b-row>
            <b-row>
                <b-container
                fluid>
                <b-row class="mt-1">
                  <b-col>
                    <b-icon
                      class="float-right pointer"
                      icon="file-plus-fill"
                      variant="primary"
                      style="height:31px; width:31px;"
                      @click="rowAdd"
                    />

                    <b-icon
                      class="float-right pointer"
                      icon="file-minus"
                      variant="primary"
                      style="height:31px; width:31px;"
                      @click="rowDelete"
                    />
                  </b-col>
                </b-row>
                <b-row class="mt-1">
                  <b-col style="padding-right: 0px; padding-left: 0px;">
                    <grid
                      ref="tuiGrid"
                      :columns="columns"
                      :options="gridOptions"
                      :columnOptions="columnOptions"
                      :data="application.applicationLogList"
                      language="ko"
                      style="height:400px"
                      @editingFinish="rowChange"/>
                  </b-col>
                </b-row>

                <b-row class="mt-2">
                  <b-col>
                    <b-button
                      class="float-right mr-1"
                      variant="outline-primary"
                      size="sm"
                      @click="back"
                    >취소</b-button>
                    <b-button
                      class="float-right mr-1"
                      variant="outline-primary"
                      size="sm"
                      @click="register"
                    >{{ saveButtonName }}</b-button>
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
import CustomerSearchBox from '@/components/hpm/customer/CustomerSearchBox'

export default {
  components: {
    Grid,
    CustomerSearchBox
  },
  data: () => ({
    gridOptions: { bodyHeight: 'fitToParent', rowHeaders: ['checkbox','rowNum'] },
    columnOptions: { resizable: true },
    companis: [],
    uploadFileImg: null,
    application: {
      id: null,
      customer: { id: 0 },
      requestCustomer: null,
      requestCustomerName: '',
      requestCustomerAddress: '',
      customerSameYn: 'Y',
      fieldCorrectionNeedYn: 'N',
      recCalibrationDayYn: 'N',
      appliRegDateType: null,
      appliRegDate: '',
      applicant: '',
      applicantEmail: '',
      consignee: '',
      takeOverDate: '',
      takeOverType: null,
      regDate: '',
      regMember: { id: null },
      customerSignImgFile: [],
      applicationLogList: [],
      technicalManager: '',
      reportLanguage: null,
      deliveryInfo: '',
      regNm: ''
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
      { text: '반출', value: 'EXPORT' },
      { text: '현장+반출', value: 'SPOTEXPORT' }
    ],
    options3: [
      { text: '직접전달', value: 'DIRECTLY' },
      { text: '고객방문', value: 'VISIT' },
      { text: '택배', value: 'DELIVERY' }
    ],
    languageOptions: [
      { text: '선택하세요', value: null },
      { text: '국문', value: 'KO' },
      { text: '영문', value: 'EN' },
      { text: '국문, 영문', value: 'KOEN' }
    ]
  }),
  computed: {
    saveButtonName: function () {
      if (this.application.id) {
        return '저장'
      } else {
        return '접수'
      }
    }
  },
  async created () {
    this.columns = [
        { header: '접수번호', name: 'regNumber', editor: 'text', width: 100, align:'center' },
        { header: '성적서 번호', name: 'reportNumber', editor: 'text', width: 100, align:'center' },
        { header: '장비명', name: 'deviceName', editor: 'text', width: 200, align:'center', validation: { required: true, dataType: 'string' } },
        { header: '제작회사', name: 'productionCompany', editor: 'text', width: 100, align:'center' },
        { header: '모델', name: 'model', editor: 'text', width: 100, align:'center' },
        { header: '규격/RANGE', name: 'standard', editor: 'text', width: 100, align:'center' },
        { header: '분해능', name: 'resolution', editor: 'text', width: 100, align:'center' },
        { header: '기기번호', name: 'deviceNumber', editor: 'text', width: 100, align:'center' },
        { header: '수량', name: 'quantity', editor: 'text', validation: { required: true, dataType: 'number' }, width: 100, align:'center' },
        { header: '비고', name: 'etc', editor: 'text', width: 200, align:'center' },
        { header: '위탁기관',
          name: 'consignmentCompanyId',
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: []
            }
          },
          width: 100, align:'center'
        },
        { header: '교정/시험/자체',
          name: 'inspectionType',
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: [{ text: '교정', value: 'CORRECTION' }, { text: '시험', value: 'TEST' }, { text: '자체', value: 'SELF' }]
            }
          },
          width: 100, align:'center'
        },
        { header: '출장/반출/입고',
          name: 'carryType',
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: [{ text: '출장', value: 'PARTICIPATION' }, { text: '반출', value: 'EXPORT' }, { text: '입고', value: 'IMPORT' }]
            }
          },
          width: 100, align:'center'
        },
        { header: '교정료', name: 'correctionFee', editor: 'text', validation: { dataType: 'number' }, width: 100, align:'center' }
    ]
    await this.companySearch()
    this.$refs.tuiGrid.invoke('setColumns', this.columns)
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
          this.$refs.tuiGrid.invoke('resetData', this.application.applicationLogList)
        }
      })
    },
    getCustomer: function (customerId) {
      this.$http.get(`/api/customer/${customerId}`)
      .then(response => {
        this.application.customer = response.data
        this.application.applicant = this.application.customer.picName
        this.application.applicantTel = this.application.customer.picTel
        this.application.applicantEmail = this.application.customer.email
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
    rowAdd () {
      const rowCount = this.$refs.tuiGrid.invoke('getRowCount')
      const applicationLog = { id: null, consignmentCompany: null, deviceName: null, quantity: 0, correctionFee: 0, reportNumber: null, productionCompany: null, deviceNumber: null, standard: null, unit: null, correctionDate: null, place: null, practitioner: null, middleCategory: null, smallCategory: null, publishedDate: null, technicalManager: null, reportLanguage: null, edite: true, del: null }

      if(rowCount != 0) {
        const firstRow = this.$refs.tuiGrid.invoke('getRow', 0)
        applicationLog.regNumber = firstRow.regNumber
      }
      
      this.$refs.tuiGrid.invoke('appendRow', applicationLog)
    },
    rowDelete () {
      const _this = this
      const keys = this.$refs.tuiGrid.invoke('getCheckedRowKeys')
      keys.forEach(function (key) {
        const appLog = _this.$refs.tuiGrid.invoke('getRow', key)
        if (appLog.id) {
          appLog.del = true
          _this.deleteApplicationLogList.push(appLog)
        }
        _this.$refs.tuiGrid.invoke('removeRow', key)
      })
    },
    rowChange: function (data) {
      const rowkey = data.rowKey
      this.$refs.tuiGrid.invoke('setValue', rowkey, 'edite', true)
    },
    companySearch: async function () {
      await this.$http.get(`/api/companyAll`)
      .then(response => {
        const com = []
        if (response.data) {
          response.data.forEach(function (item) {
            com.push({ text: item.name, value: item.id + '' })
          })
        }
        this.columns[10].editor.options.listItems = com
        // this.companis = com;
        })
    },
    fnChangeImage: function (e) {
      const file = e.target.files[0] // Get first index in files
      this.uploadFileImg = URL.createObjectURL(file) // Create File URL
    },
    customerSameChange: function (val) {
      if (val === 'Y') {
        this.application.requestCustomer = null
      }
    },
    register: function () {
      this.$refs.tuiGrid.invoke('finishEditing')
      var validate = this.$refs.tuiGrid.invoke('validate')

      if (this.application.customer.id === 0) {
        alert('신청인 회사 데이터가 없습니다.')
        return
      } else if (!this.application.appliRegDate) {
        alert('접수날짜을 입력해주세요.')
        return
      } else if (!this.application.applicant) {
        alert('신청인을 입력해주세요.')
        return
      }else if (this.application.customerSameYn != "Y" && this.application.requestCustomer == null) {
        alert('성적서 발행 업체를 선택해주세요.')
        return
      }
      /*else if (this.application.takeOverType.key == '') {
        alert('인수방법을 선택해주세요.')
        return
      }*/
      if (validate.length > 0) {
        alert('값검증이 안된 셀이 있습니다.')
        return false
      }
        
      if (confirm('신청서를 등록/저장하시겠습니까?')) {
        const formData = new FormData()
        this.application.applicationLogList = [...this.$refs.tuiGrid.invoke('getData'), ...this.deleteApplicationLogList]

        //this.application.regMember.id = this.$store.state.user.id
        const application = this.application
        if (application['customerSignImgFile']) {
          formData.append('customerSignImgFile', application['customerSignImgFile'])
        }
        const json = JSON.stringify(application)
          const blob = new Blob([json], {
            type: 'application/json'
        })
        formData.append('applicationVO', blob)

        this.$http.post(`/api/application`, formData)
        .then(response => {
          if (response.data.result === 'success') {
            alert(response.data.msg)
            this.$router.push(`/dashboard/application/view?id=${response.data.id}`)
          } else {
            alert(response.data.msg)
          }
          })
        .catch(function( ) {
          alert('에러가발생했습니다.')
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
    back: function(){
      window.history.back();
    },
    setRequestCustomer: function(customer){
      this.application.requestCustomer = customer
      this.$bvModal.hide('modal-1')
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
  .bgW { background-color:white !important;}
  .appCol {border: 1px solid #dee2e6;}
</style>
