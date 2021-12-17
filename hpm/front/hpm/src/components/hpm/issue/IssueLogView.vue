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
              <b-col class="f25">접수대장 이력</b-col>
            </b-row>
            <b-row>
              <b-container
                fluid>
                <b-row class="mt-1">
                  <b-col>
                      <b-form-select
                        v-model="dateCondition"
                        class="fl mr-2 mb-2 col-1">
                        <b-form-select-option value="excel">엑셀등록일</b-form-select-option>
                        <b-form-select-option value="appliRegDate">교정일</b-form-select-option>
                        <b-form-select-option value="correctionDate">교정일</b-form-select-option>
                        <b-form-select-option value="publishedDate">발행일</b-form-select-option>
                      </b-form-select>
                      <b-form-select
                        v-model="hpmType"
                        class="fl mr-2 mb-2 col-1">
                        <b-form-select-option value="">전체</b-form-select-option>
                        <b-form-select-option value="HPM">HPM</b-form-select-option>
                        <b-form-select-option value="N">N</b-form-select-option>
                        <b-form-select-option value="자체">자체</b-form-select-option>
                      </b-form-select>
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
                        v-on:keydown.enter="getiIssueLogList()"
                        placeholder="키워드를 입력해주세요." />

                      <b-button
                        variant="outline-primary"
                        class="mr-2 mb-2"
                        @click="getiIssueLogList">조회</b-button>
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
                      :data="issueLogList"
                      @editingFinish="rowChange"
                      style="height:400px"
                      language="ko"
                    />
                  </b-col>
                </b-row>
                <b-row>
                  <b-form inline>
                    <b-form-file
                        id="file-small"
                        v-model="excelFile"
                        style="width:300px"
                        class="mt-2 mr-2"
                        size="sm"
                        browse-text="엑셀파일"
                        placeholder=""
                        color="primary"
                      />
                  </b-form>
                  <b-button
                      class="mt-2 mr-2"
                      variant="outline-primary"
                      @click="excelUpload">엑셀 업로드</b-button>
                  <b-button
                      class="mt-2 mr-2"
                      variant="outline-success"
                      @click="excelDownload">엑셀 다운로드</b-button>
                  <b-button
                    class="mt-2 ml-2"
                    variant="outline-primary"
                    @click="issueLogModify">수정</b-button>
                  <b-button
                    class="mt-2 ml-2"
                    variant="outline-danger"
                    @click="issueLogDelete">삭제</b-button>
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
    issueLogList: [],
    condition: "",
    dateCondition: "excel",
    keyword: "",
    startDate: "",
    endDate: "",
    excelFile: null,
    issueLogEditeList: [],
    reportLanguage: {"KO":"국문","EN":"영문","KOEN":"국문, 영문"},
    hpmType: ""
  }),
  computed: {
   
  },
  async created () {
    const _this = this;
    this.columns = [
        {name: 'appliRegDate', header: '접수일', width: 100, editor: 'text' },
        {name: 'regNumber', header: '접수번호', width: 100, editor: 'text' },
        {name: 'reportNumber', header: '성적서 번호',width: 150, editor: 'text' },
        {name: 'requestCustomerName', header: '의뢰업체', width: 200 },
        {name: 'customer', header: '업체명', width: 200 , formatter: function(data){ return (data.value)?data.value.name:'' }},
        {name: 'deviceName', header: '장비명', width: 200, editor: 'text' },
        {name: 'productionCompany', header: '제작회사', width: 150, editor: 'text' },
        {name: 'model', header: '형식(모델)', width: 150, editor: 'text' },
        {name: 'deviceNumber', header: '기기번호', width: 150, editor: 'text' },
        {name: 'standard', header: '규격/RANGE', width: 150, editor: 'text' },
        {name: 'resolution', header: '분해능', width: 150, editor: 'text' },
        {name: 'unit', header: '단위', width: 100, editor: 'text' },
        {name: 'correctionDate', header: '교정일', width: 150, editor: { type: 'datePicker', options: { language: 'ko' } } },
        {name: 'place', header: '장소', width: 150, editor: 'text' },
        {name: 'practitioner', header: '실무자', width: 150, editor: 'text' },
        {name: 'middleCategory', header: '중분류', width: 150, editor: 'text' },
        {name: 'smallCategory', header: '소분류', width: 150, editor: 'text' },
        {name: 'publishedDate', header: '발행일자', width: 150, editor: { type: 'datePicker', options: { language: 'ko' } } },
        {name: 'technicalManager', header: '기술책임자', width: 150, editor: 'text' },
        {name: 'reportLanguage', header: '성적서 언어', width: 200  , formatter: function(data){ return _this.reportLanguage[data.value]; }},
        {name: 'correctionFee', header: '교정료', width: 150, editor: 'text' },
        {name: 'hpmType', header: 'HPM구분', width: 150 },
        {name: 'regDt', header: '엑셀등록일', width: 150 }
        
        /*{name: 'quantity', header: '수량', width: 100, editor: 'text' },
        {name: 'etc', header: '비고', width: 200, editor: 'text' },
        {name: 'customerPicName', header: '담당자', width: 150 },
        {name: 'customerMobile', header: '연락처', width: 150 },
        {name: 'customerTel', header: '회사전화번호', width: 150 },
        {name: 'customerFax', header: '회사팩스번호', width: 150 },
        {name: 'customerEmail', header: '이메일주소', width: 200 },
        {name: 'paymentStateType', header: '결제상태', width: 80 ,
          formatter: 'listItemText',
            editor: {
              type: 'select',
              options: {
                listItems: [{ text: '미결제', value: 'NONE' }, { text: '결제', value: 'PAY' }]
              }
            } },
        {name: 'billPicName', header: '결제담당자', width: 150 },
        {name: 'billPicTel', header: '결제담당전화번호', width: 150 },
        {name: 'billPicMail', header: '결제담당이메일주소', width: 150 },*/
    ]

    await this.companySearch()

    this.$refs.tuiGrid.invoke('setColumns', this.columns)
  },
  beforeMount () {
    this.startDate = this.$moment().subtract(3,'M').format("YYYY-MM-DD")
    this.endDate = this.$moment().format("YYYY-MM-DD")
    this.getiIssueLogList()
  },
  methods: {
    getiIssueLogList: function () {

      this.$http.get(`/api/issue?condition=${this.condition}&keyword=${this.keyword}&startDate=${this.startDate}&endDate=${this.endDate}&dateCondition=${this.dateCondition}&hpmType=${this.hpmType}`)
      .then(response => {
        if (response) {
          this.issueLogList = response.data
        // const rowData = []
        this.issueLogList.forEach(function(item){

            if(item.customer.picName) item.customerPicName = item.customer.picName
            if(item.customer.mobile) item.customerMobile = item.customer.mobile
            if(item.customer.tel) item.customerMobile = item.customer.tel
            if(item.customer.fax) item.customerFax = item.customer.fax
            if(item.customer.email) item.customerFax = item.customer.email

            if(item.customer.billPicMail) item.billPicName = item.customer.billPicMail
            if(item.customer.billPicName) item.billPicName = item.customer.billPicName
            if(item.customer.billPicTel) item.billPicTel = item.customer.billPicTel
            
            if(item.inspectionType) item.customerEmail = item.inspectionType.key
            if(item.carryType) item.carryType = item.carryType.key
        })

          this.$refs.tuiGrid.invoke('resetData', this.issueLogList)
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
        //this.columns[18].editor.options.listItems = com
        // this.companis = com;
        })
    },
    onRowSelected(items) {
      this.selected = items
    },
    excelUpload() {
      if (confirm('엑셀을 업로드 하시겠습니까?')) {
        const formData = new FormData()
        if (this.excelFile) {
          formData.append('excelFile', this.excelFile)
        }
        this.$http.post(`/api/issue`, formData)
        .then(response => {
          if (response.data.result === 'success') {
            alert(response.data.msg)
            this.getiIssueLogList()
          } else {
            alert(response.data.msg)
          }
          this.getiIssueLogList()
          })
      }
    },
    excelDownload: function () {
      this.$http.get(`/api/issue/excel?condition=${this.condition}&keyword=${this.keyword}&startDate=${this.startDate}&endDate=${this.endDate}&dateCondition=${this.dateCondition}&hpmType=${this.hpmType}`
          , { responseType: 'blob' }).then(response => {
          const blob = new Blob(
					[response.data], { type: 'application/vnd.ms-excel;charset=utf-8' })
                    const aEle = document.createElement('a');     // Create a label
                    const href = window.URL.createObjectURL(blob);       // Create downloaded link
                    aEle.href = href
                    
                    aEle.download = "임시관리대장";  // File name after download
                    document.body.appendChild(aEle);
                    aEle.click();     // Click to download
                    document.body.removeChild(aEle); // Download complete remove element
                    window.URL.revokeObjectURL(href) // Release blob object
          
        }).catch(err => console.log(err))
    },
    rowChange: function (data) {
      const rowkey = data.rowKey
      const row = this.$refs.tuiGrid.invoke('getRow', rowkey)
      let found = false
      for(let i = 0 ; i < this.issueLogEditeList.length  ; i++){
        if(this.issueLogEditeList[i].id == row.id){
          this.issueLogEditeList[i] = row
          console.log(this.issueLogEditeList)
          found = true
          return 
        }
      }

      if(!found){
        this.issueLogEditeList.push(row)
      }

      console.log(this.issueLogEditeList)
    },
    issueLogModify() {
      this.$refs.tuiGrid.invoke('finishEditing')
      if (confirm('저장하시겠습니까?')) {
        this.$http.put(`/api/issue`, this.issueLogEditeList)
        .then(response => {
          if (response.data.result === 'success') {
            alert(response.data.msg)
            this.issueLogEditeList = []
          } else {
            alert(response.data.msg)
          }
          })
      }
    },
    issueLogDelete() {
      this.$refs.tuiGrid.invoke('finishEditing')
      if (confirm('삭제하시겠습니까?\n다시는 복구할 수 없습니다.')) {
      const issueLogEditeList = this.$refs.tuiGrid.invoke('getCheckedRows')
        this.$http.delete(`/api/issue`, { data: issueLogEditeList })
        .then(response => {
          if (response.data.result === 'success') {
            alert(response.data.msg)
            this.getiIssueLogList()
          } else {
            alert(response.data.msg)
          }
          })
        .catch(function(){
          alert('에러가 발생했습니다.')
        })
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
