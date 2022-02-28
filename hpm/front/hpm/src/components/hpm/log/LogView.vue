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
            <b-row class="mb-3">
              <b-col class="f25">대장 이력</b-col>
            </b-row>
            <b-row>
              <b-container
                fluid>
                <b-row class="mt-1">
                  <b-col>
                      <span class="fl mb-2 mr-2">접수일 : </span>
                      
                      <b-form-input
                        id="example-input"
                        v-model="startDate"
                        :readonly="true"
                        type="text"
                        placeholder="시작일"
                        autocomplete="off"
                        size="sm"
                        class="fl mb-2"
                        style="width:120px;"
                      />
                      <b-form-datepicker
                        v-model="startDate"
                        button-only
                        locale="ko-KR"
                        aria-controls="example-input"
                        size="sm"
                        class="fl mr-2"
                      />
                      <b-form-input
                        id="example-input"
                        v-model="endDate"
                        :readonly="true"
                        type="text"
                        placeholder="종료일"
                        autocomplete="off"
                        size="sm"
                        class="fl"
                        style="width:120px;"
                      />
                      <b-form-datepicker
                        v-model="endDate"
                        button-only
                        locale="ko-KR"
                        aria-controls="example-input"
                        size="sm"
                        class="fl mr-2"
                      />
                  </b-col>
                </b-row>
                <b-row class="mt-1">
                  <b-col
                    class="pr-0">
                    <b-form inline v-on:submit.prevent>
                      <b-form-select
                        v-model="condition"
                        class="mr-2 mb-2 col-2">
                        <b-form-select-option value="">전체</b-form-select-option>
                        <b-form-select-option value="customerName">거래처명</b-form-select-option>
                        <b-form-select-option value="regNumber">접수번호</b-form-select-option>
                        <b-form-select-option value="deviceName">장비명</b-form-select-option>
                        <b-form-select-option value="productionCompany">제조사</b-form-select-option>
                        <b-form-select-option value="model">모델</b-form-select-option>
                        <b-form-select-option value="deviceNumber">기기번호</b-form-select-option>
                      </b-form-select>

                      <b-form-input
                        id="customerName"
                        v-model="keyword"
                        type="text"
                        class="mr-2 mb-2 col-4"
                        v-on:keydown.enter="getApplication()"
                        placeholder="키워드를 입력해주세요." />

                      <b-button
                        variant="outline-primary"
                        class="mr-2 mb-2"
                        @click="getApplication">조회</b-button>
                    </b-form>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col>
                  <grid
                      ref="tuiGrid"
                      :columns="columns"
                      :options="gridOptions"
                      :columnOptions="columnOptions"
                      :data="applicationLogList"
                      style="height:400px"
                      language="ko"
                    />
                  </b-col>
                </b-row>
                <b-row>
                  <b-button
                    class="mt-2 ml-2"
                    variant="outline-primary"
                    @click="dataDownload">견적</b-button>
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
    stickyHeader: true,
    noCollapse: true,
    selectMode: 'multi',
    selected: [],
    striped: false,
    gridOptions: { bodyHeight: 'fitToParent', rowHeaders: ['checkbox','rowNum']},
    columnOptions: { resizable: true },
    applicationLogList: [],
    condition: "",
    keyword: "",
    startDate: "",
    endDate: "",
    appLogEditeList: []
  }),
  computed: {
   
  },
  async created () {

    this.columns = [
        {name: 'appliRegDate', header: '접수일', width: 150, align:'center' },
        {name: 'regNumber', header: '접수번호', width: 100 },
        {name: 'reportNumber', header: '성적서 번호',width: 150 },
        {name: 'requestCustomerName', header: '의뢰업체', width: 200 },
        {name: 'customerName', header: '업체명', width: 200 },
        {name: 'deviceName', header: '장비명', width: 200 },
        {name: 'productionCompany', header: '제작회사', width: 150 },
        {name: 'model', header: '형식(모델)', width: 150 },
        {name: 'deviceNumber', header: '기기번호', width: 150 },
        {name: 'standard', header: '규격/RANGE', width: 150 },
        {name: 'correctionDate', header: '교정일', width: 150 },
        {name: 'quantity', header: '수량', width: 100},
        {name: 'correctionFee', header: '교정료', width: 150},
        {name: 'etc', header: '비고', width: 200},
        {name: 'consignmentCompanyNm', header: '위탁기관', width: 100 },
        {name: 'inspectionType', header: '교정/시험/자체', width: 100 },
        {name: 'carryType', header: '출장/반출/입고', width: 200 },
        {name: 'paymentStateType', header: '결제상태', width: 80 },
        {name: 'takeOverType', header: '성적서 발송', width: 80, formatter: function(data){
          if(data.value){
            return data.value.value
          }
        }},
        {name: 'deliveryInfo', header: '성적서 발송정보', width: 300 },
        {name: 'customerPicName', header: '담당자', width: 150 },
        {name: 'customerMobile', header: '연락처', width: 150 },
        {name: 'customerTel', header: '회사전화번호', width: 150 },
        {name: 'customerFax', header: '회사팩스번호', width: 150 },
        {name: 'customerEmail', header: '이메일주소', width: 200 },
        {name: 'customerBillPicName', header: '결제담당자', width: 150 },
        {name: 'customerBillPicTel', header: '결제담당전화번호', width: 150 },
        {name: 'customerBillPicMail', header: '결제담당이메일주소', width: 150 },
        
        {name: 'resolution', header: '분해능', width: 150 },
        {name: 'unit', header: '단위', width: 100 },
        {name: 'place', header: '교정장소', width: 150 },
        {name: 'practitioner', header: '실무자', width: 150 },
        {name: 'middleCategory', header: '중분류', width: 150 },
        {name: 'smallCategory', header: '소분류', width: 150 },
        {name: 'publishedDate', header: '발행일자', width: 150 },
        {name: 'technicalManager', header: '기술책임자', width: 150 },
        
        //{name: 'reportLanguage', header: '성적서 언어', width: 200 },
    ]

    await this.companySearch()

    this.$refs.tuiGrid.invoke('setColumns', this.columns)
  },
  beforeMount () {
    this.startDate = this.$moment().subtract(3,'M').format("YYYY-MM-DD")
    this.endDate = this.$moment().format("YYYY-MM-DD")
    this.getApplication()
  },
  methods: {
    getApplication: function () {

      this.$http.get(`/api/logAll?condition=${this.condition}&keyword=${this.keyword}&startDate=${this.startDate}&endDate=${this.endDate}`)
      .then(response => {
        if (response) {
          this.applicationLogList = response.data
        // const rowData = []
        this.applicationLogList.forEach(function(item){

            if(item.consignmentCompany) {
              item.consignmentCompany = item.consignmentCompany + ''
            }

            if(item.inspectionType) item.inspectionType = item.inspectionType.key
            if(item.carryType) item.carryType = item.carryType.key

        })

          this.$refs.tuiGrid.invoke('resetData', this.applicationLogList)
          this.appLogEditeList = []
        }
        })
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
        this.columns[18].editor.options.listItems = com
        // this.companis = com;
        })
    },
    onRowSelected(items) {
      this.selected = items
    },
    dataDownload(){
      const dataList = []
      const _this = this
      const keys = this.$refs.tuiGrid.invoke('getCheckedRowKeys')

      if(keys.length == 0){
        alert("데이터를 선택해주세요")
        return
      }

      keys.forEach(function (key) {
        const appLog = _this.$refs.tuiGrid.invoke('getRow', key)
        var copy_object = Object.assign({}, appLog);
        copy_object.consignmentCompany = {id: copy_object.consignmentCompany}
        copy_object.inspectionType = null
        copy_object.carryType = null
          dataList.push(copy_object)
      })
      this.$http.post(`/api/applicationlog/excel`, dataList ,{ responseType: 'blob' }).then(response => {
          const blob = new Blob(
					[response.data], { type: 'application/vnd.ms-excel.sheet.macroEnabled.12;charset=utf-8' })
                    const aEle = document.createElement('a');     // Create a label
                    const href = window.URL.createObjectURL(blob);       // Create downloaded link
                    aEle.href = href;
                    aEle.download = '교정견적서 작성 2022';//title+' '+this.$moment().format("YYYY년 MM월 DD일")+' '+dataList[0].customerName
                    document.body.appendChild(aEle);
                    aEle.click();     // Click to download
                    document.body.removeChild(aEle); // Download complete remove element
                    window.URL.revokeObjectURL(href) // Release blob object
        }).catch(err => console.log(err))
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
