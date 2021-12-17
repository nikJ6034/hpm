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
            fluid
          >
            <b-row>
              <b-col class="f25">위탁업체 신청서</b-col>
            </b-row>
            <b-row>
              <b-container
                fluid>
                <b-row class="mt-1">
                  <b-col>
                    <grid
                      ref="tuiGrid"
                      :columns="columns"
                      :data="consignmentCompanyApplication.applicationLogList"
                      :options="gridOptions"
                      style="height:400px"
                      language="ko"
                    />
                  </b-col>
                </b-row>

                <b-row class="mt-2">
                  <b-col>
                    <b-button
                      v-if="consignmentCompanyApplication.id != 0"
                      class="float-right mr-1"
                      variant="outline-danger"
                      size="sm"
                      @click="remove"
                    >삭제</b-button>

                    <b-button
                      v-if="consignmentCompanyApplication.id == 0"
                      class="float-right mr-1"
                      variant="outline-primary"
                      size="sm"
                      @click="register"
                    >등록</b-button>

                    <b-button
                      v-if="consignmentCompanyApplication.id != 0"
                      class="float-right mr-1"
                      variant="outline-primary"
                      size="sm"
                      @click="appExcelDown"
                    >신청서 보기</b-button>
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
    gridOptions: { bodyHeight: 'fitToParent' },
    companis: [],
    uploadFileImg: null,
    consignmentCompanyApplication: { id: 0, consignmentCompany: { id: 0, name: '' }, startDt: '', endDt: '', applicationLogList: [], regMember: { id: null } },
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
    ],
    columns: []
  }),
  computed: {

  },
  async created () {
    this.columns = [
        { header: '사업자', name: 'consignmentCompany', formatter: function(data){  return data.value.name } },
        { header: '기기명', name: 'deviceName' },
        { header: '제작 회사', name: 'productionCompany' },
        { header: '모델', name: 'model', align:'center' },
        { header: '수량', name: 'quantity' },
        { header: '업체명', name: 'customerName'
          , formatter: function(data){
            if(data.value == 'N'){
              return data.row.requestCustomerName
            }else{
              return data.row.customerName
            }
          } 
        },
        { header: '주소', name: 'customerAdress'
          , formatter: function(data){
            if(data.value == 'N'){
              return data.row.requestCustomerAddress
            }else{
              return data.row.customerAdress + (data.row.customerAdressDetail||"")
            }
          } 
        },
        { header: '성적서발행 업체명', name: 'requestCustomerName', width: 150 },
        { header: '성적서발행 업체 주소', name: 'requestCustomerAddress', width: 300 },
        { header: '비고', name: 'etc', align:'center' },
        // { header: '성적서 번호', name: 'reportNumber' },
        // { header: '기기번호', name: 'deviceNumber' },
        // { header: '규격', name: 'standard' },
        // { header: '단위', name: 'unit' },
        // { header: '교정일자', name: 'correctionDate' },
        // { header: '장소', name: 'place' },
        // { header: '실무자', name: 'practitioner' },
        // { header: '중분류', name: 'middleCategory' },
        // { header: '소분류', name: 'smallCategory' },
        // { header: '발행일자', name: 'publishedDate' },
        // { header: '기술책임자', name: 'technicalManager' },
        // { header: '성적서 언어', name: 'reportLanguage' }
    ]
    // this.$refs.tuiGrid.invoke('setColumns', this.columns);
  },
  beforeMount () {
    const id = this.$route.query.id
    const companyId = this.$route.query.companyId
    const startDt = this.$route.query.startDt
    const endDt = this.$route.query.endDt
    if (id) {
    this.consignmentCompanyApplication.id = id
    }
    if (companyId) {
      this.consignmentCompanyApplication.consignmentCompany.id = companyId
    }

    if (startDt) {
      this.consignmentCompanyApplication.startDt = startDt
    }

    if (endDt) {
      this.consignmentCompanyApplication.endDt = endDt
    }
    this.getApplication()
  },
  methods: {
    getApplication: function () {
      this.$http.get(`/api/companyApp/${this.consignmentCompanyApplication.id}?consignmentCompany.id=${this.consignmentCompanyApplication.consignmentCompany.id}&startDt=${this.consignmentCompanyApplication.startDt}&endDt=${this.consignmentCompanyApplication.endDt}`)
      .then(response => {
        if (response) {
          this.consignmentCompanyApplication = response.data
          this.$refs.tuiGrid.invoke('resetData', this.consignmentCompanyApplication.applicationLogList)
        }
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
    /* signImageUrl: function(){
      this.$http.get(`/api/img/file/${this.consignmentCompanyApplication.customerSignImg.id}`,{ responseType:"blob" }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
            this.uploadFileImg = url
      }).catch(err => console.log(err));
    }, */
    register: function () {
      if(!confirm('등록하시겠습니까?')){
        return ;
      }else{
        this.consignmentCompanyApplication.regMember = { id: this.$store.state.user.id }
        this.consignmentCompanyApplication.applicationLogList = []
        this.$http.post(`/api/companyApp`, this.consignmentCompanyApplication)
        .then(response => {
          if (response.data.result == 'success') {
            alert(response.data.msg)
            this.$router.push(`/dashboard/consign/write?id=${response.data.id}`)
            location.reload()
          } else {
            alert(response.data.msg)
          }
        })
      }
        
    },
    remove: function () {
      if (confirm('삭제후에는 복구가 불가능합니다. 삭제하시겠습니까?')) {
      this.$http.delete(`/api/companyApp`, { data: this.consignmentCompanyApplication })
      .then(response => {
        if (response.data.result === 'success') {
          alert('삭제되었습니다.')
          this.$router.push(`/dashboard/consign`)
        }
        })
      }
    },
    appExcelDown: function () {
      this.$http.get(`/api/excel/companyApp/${this.consignmentCompanyApplication.id}`, { responseType: 'blob' }).then(response => {
          const blob = new Blob(
					[response.data], { type: 'application/vnd.ms-excel;charset=utf-8' })
                    const aEle = document.createElement('a');     // Create a label
                    const href = window.URL.createObjectURL(blob);       // Create downloaded link
                    aEle.href = href;
                    aEle.download = this.consignmentCompanyApplication.consignmentCompany.name+" "+this.$moment().format("YYYY년 MM월 DD일")+'.xls';  // File name after download
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
