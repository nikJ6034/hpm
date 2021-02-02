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
                            @change="customerSameChange"
                          />
                        </b-form-group>
                      </b-td>
                      <b-th>업체명</b-th>
                      <b-td>
                        <b-form-input
                          :readonly="application.customerSameYn == 'Y'"
                          v-model="application.requestCustomerName"
                          placeholder="업체명을 입력하세요." />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>주소</b-th>
                      <b-td>
                        <b-form-input
                          :readonly="application.customerSameYn == 'Y'"
                          v-model="application.requestCustomerAddress"
                          placeholder="주소를 입력하세요." />
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
                          name="radios-stacked2"
                          class="fl mr-1"
                        />
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
                      </b-td>
                      <b-th>신청인</b-th>
                      <b-td>
                        <b-form-input
                          v-model="application.applicant"
                          placeholder="신청인을 입력하세요." />
                      </b-td>
                      <b-td rowspan="3">
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
                        <b-form-input
                          v-model="application.applicantEmail"
                          placeholder="이메일를 입력하세요." />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>인수방법</b-th>
                      <b-td>
                        <b-form-radio-group
                          v-model="application.takeOverType"
                          :options="options3"
                          name="radios-stacked3"
                          class="fl mr-1"
                        />
                      </b-td>
                      <b-th>
                        서명
                      </b-th>
                      <b-td>
                        <!-- accept="image/*" -->
                        <b-form-file
                          id="file-small"
                          v-model="application.customerSignImgFile"
                          style="width:450px;float:right;"
                          size="sm"
                          browse-text="신청인 서명 업로드"
                          placeholder="서명 파일을 첨부해주세요."
                          class="float-right mr-3"
                          color="primary"
                          @change="fnChangeImage"
                        />
                      </b-td>
                    </b-tr>
                  </b-tbody>
                </b-table-simple>

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
                    <b-col>
                      <grid
                        ref="tuiGrid"
                        :columns="columns"
                        :options="gridOptions"
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
    gridOptions: { bodyHeight: 'fitToParent', rowHeaders: ['checkbox'] },
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
      regDate: '',
      regMember: { id: null },
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
        return '저장'
      } else {
        return '접수'
      }
    }
  },
  async created () {
    this.columns = [
        { header: '사업자',
          name: 'consignmentCompany',
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: []
            }
          }
        },
        { header: '기기명', name: 'deviceName', editor: 'text' },
        { header: '수량', name: 'quantity', editor: 'text', validation: { dataType: 'number' } },
        { header: '성적서 번호', name: 'reportNumber', editor: 'text' },
        { header: '제작 회사', name: 'productionCompany', editor: 'text' },
        { header: '기기번호', name: 'deviceNumber', editor: 'text' },
        { header: '규격', name: 'standard', editor: 'text' },
        { header: '단위', name: 'unit', editor: 'text' },
        { header: '교정일자', name: 'correctionDate', editor: { type: 'datePicker', options: { language: 'ko' } } },
        { header: '장소', name: 'place', editor: 'text' },
        { header: '실무자', name: 'practitioner', editor: 'text' },
        { header: '중분류', name: 'middleCategory', editor: 'text' },
        { header: '소분류', name: 'smallCategory', editor: 'text' },
        { header: '발행일자', name: 'publishedDate', editor: { type: 'datePicker', options: { language: 'ko' } } },
        { header: '기술책임자', name: 'technicalManager', editor: 'text' },
        { header: '성적서 언어',
          name: 'reportLanguage',
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: [{ text: '한국어', value: 'KO' }, { text: '영어', value: 'EN' }]
            }
          }
        }
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
          const logList = []
          this.application.applicationLogList.forEach(function (item) {
            item.consignmentCompany = item.consignmentCompany.id + ''
            item.edite = false
            item.del = false
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
    rowAdd () {
      const applicationLog = { id: null, consignmentCompany: null, deviceName: null, quantity: 0, reportNumber: null, productionCompany: null, deviceNumber: null, standard: null, unit: null, correctionDate: null, place: null, practitioner: null, middleCategory: null, smallCategory: null, publishedDate: null, technicalManager: null, reportLanguage: null, edite: true, del: null }
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
        this.columns[0].editor.options.listItems = com
        // this.companis = com;
        })
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
      this.$refs.tuiGrid.invoke('finishEditing')
      var validate = this.$refs.tuiGrid.invoke('validate')

      if (this.application.customer.id === 0) {
        alert('신청인 회사 데이터가 없습니다.')
        return
      } else if (!this.application.appliRegDateType) {
        alert('기준일 타입을 선택해주세요.')
        return
      } else if (!this.application.appliRegDate) {
        alert('기준일을 입력해주세요.')
        return
      } else if (!this.application.applicant) {
        alert('신청인을 입력해주세요.')
        return
      }

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
