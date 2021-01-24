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
              fluid
              >
              <b-row>
                <b-col class="f25">신청서 목록</b-col>
              </b-row>

              <hr/>

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
                      <b-td class="text-left"></b-td>
                      <b-th class="text-center">대표자</b-th>
                      <b-td class="text-left" colspan="3"></b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">사업자등록번호</b-th>
                      <b-td class="text-left"></b-td>
                      <b-th class="text-center">업태</b-th>
                      <b-td class="text-left"></b-td>
                      <b-th class="text-center">종목</b-th>
                      <b-td class="text-left"></b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">주소</b-th>
                      <b-td class="text-left" colspan="5"></b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">전화번호</b-th>
                      <b-td class="text-left"></b-td>
                      <b-th class="text-center">팩스번호</b-th>
                      <b-td class="text-left" colspan="3"></b-td>
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
                      <b-td rowspan="2" class="text-center">
                          <b-form-radio-group
                            :options="options2"
                            name="radios-stacked2"
                            class="fl mr-1"
                          ></b-form-radio-group>

                          <b-form-input
                            id="example-input"
                            :readonly="true"
                            type="text"
                            placeholder="YYYY-MM-DD"
                            autocomplete="off"
                            size="sm"
                            class="fl"
                            style="width:120px;"
                          ></b-form-input>
                          <b-form-datepicker
                              button-only
                              locale="ko-KR"
                              aria-controls="example-input"
                              size="sm"
                              class="fl"
                            ></b-form-datepicker>
                      </b-td>
                      <b-th>신청인</b-th>
                      <b-td>
                        <b-form-input
                          placeholder="신청인을 입력하세요." />
                      </b-td>
                      <b-td rowspan="3">
                        <b-img :src="uploadFileImg" width="100px;" height="100px;" alt="서명 이미지" />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>이메일</b-th>
                      <b-td>
                        <b-form-input
                          placeholder="이메일를 입력하세요." />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>인수방법</b-th>
                      <b-td>
                        <b-form-radio-group
                          :options="options3"
                          name="radios-stacked3"
                          class="fl mr-1"
                        ></b-form-radio-group>
                      </b-td>
                      <b-th>
                        서명
                      </b-th>
                      <b-td>
                        <!-- accept="image/*" -->
                        <b-form-file id="file-small" 
                          style="width:450px;float:right;" 
                          size="sm" 
                          browse-text="신청인 서명 업로드" 
                          placeholder="서명 파일을 첨부해주세요." 
                          class="float-right mr-3"
                          color="primary"
                          @change="fnChangeImage"
                        ></b-form-file>
                      </b-td>
                    </b-tr>
                  </b-tbody>
                </b-table-simple>

                <b-container
                  fluid>
                  <b-row class="mt-1">
                    <b-col>
                      <grid style="height:400px" ref="tuiGrid" :columns="columns" language="ko" :data="consignmentCompanyApplication.applicationLogList" :options="gridOptions" />
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
                      >등록</b-button>

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
    gridOptions: {bodyHeight:'fitToParent'},
    companis: [],
    uploadFileImg: null,
    consignmentCompanyApplication: { id: 0, consignmentCompany: {id: 0, name: ''}, startDt: '', endDt: '', applicationLogList: [], regMember: {id:null} },
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
  async created () {
    const _this = this;
    this.columns = [
        {  header: '사업자', name:"consignmentCompanyName"},
        { header: '기기명', name:"deviceName"},
        { header: '수량', name:"quantity"},
        { header: '성적서 번호', name:"reportNumber"},
        { header: '제작 회사', name:"productionCompany"},
        { header: '기기번호', name:"deviceNumber"},
        { header: '규격', name:"standard"},
        { header: '단위', name:"unit"},
        { header: '교정일자', name:"correctionDate"},
        { header: '장소', name:"place"},
        { header: '실무자', name:"practitioner"},
        { header: '중분류', name:"middleCategory"},
        { header: '소분류', name:"smallCategory"},
        { header: '발행일자', name:"publishedDate"},
        { header: '기술책임자', name:"technicalManager"},
        { header: '성적서 언어', name:"reportLanguage"}
    ]
    
    //this.$refs.tuiGrid.invoke('setColumns', this.columns);
  },
  beforeMount () {
    const id = this.$route.query.id
    const companyId = this.$route.query.companyId
    const startDt = this.$route.query.startDt
    const endDt = this.$route.query.endDt
    if(id){
    this.consignmentCompanyApplication.id = id
    }
    if(companyId){
      this.consignmentCompanyApplication.consignmentCompany.id = companyId
    }

    if(startDt){
      this.consignmentCompanyApplication.startDt = startDt
    }

    if(endDt){
      this.consignmentCompanyApplication.endDt = endDt
    }
  
    this.getApplication()
    this.uploadFileImg = 'http://placehold.it/150x150';
  },
  methods: {
    getApplication: function(){
      this.$http.get(`/api/companyApp/${this.consignmentCompanyApplication.id}?consignmentCompany.id=${this.consignmentCompanyApplication.consignmentCompany.id}&startDt=${this.consignmentCompanyApplication.startDt}&endDt=${this.consignmentCompanyApplication.endDt}`)
      .then(response => {
        if(response){
          this.consignmentCompanyApplication = response.data;
          const logList = [];
          this.consignmentCompanyApplication.applicationLogList.forEach(function(item){
            item.consignmentCompanyName = item.consignmentCompany.name
            item.reportLanguage = item.reportLanguage.name
            logList.push(item)
          })
          this.$refs.tuiGrid.invoke('resetData',logList)
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
      const file = e.target.files[0]; // Get first index in files
      this.uploadFileImg = URL.createObjectURL(file); // Create File URL
    },
    /*signImageUrl: function(){
      this.$http.get(`/api/img/file/${this.consignmentCompanyApplication.customerSignImg.id}`,{ responseType:"blob" }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
            this.uploadFileImg = url
      }).catch(err => console.log(err));
    },*/
    register: function (){

      if(this.consignmentCompanyApplication.consignmentCompany.id == 0){
        alert("위탁업체 데이터가 없습니다.")
        return
      }else if(!this.consignmentCompanyApplication.startDt){
        alert("시작일이 존재하지 않습니다.")
        return
      }else if(!this.consignmentCompanyApplication.endDt){
        alert("종료일이 존재하지 않습니다.")
        return
      }

      if(confirm("신청서를 등록하시겠습니까?")){
        // const formData = new FormData();
        // this.consignmentCompanyApplication.regMember = {id:this.$store.state.user.id}
        // const consignmentCompanyApplication = this.consignmentCompanyApplication
        // if(consignmentCompanyApplication['customerSignImgFile']){
        //   formData.append('customerSignImgFile', application['customerSignImgFile'])
        // }
        // const json = JSON.stringify(consignmentCompanyApplication)
        //   const blob = new Blob([json], {
        //     type: 'application/json'
        // });

        // formData.append('consignmentCompanyApplication',blob)

        this.consignmentCompanyApplication.regMember = {id:this.$store.state.user.id}
        this.consignmentCompanyApplication.applicationLogList = []
        this.$http.post(`/api/companyApp`,this.consignmentCompanyApplication)
        .then(response => {
          if(response.data.result === 'success'){
            alert(response.data.msg)
          }else{
            alert(response.data.msg)
          }
          })
      }
    },
    remove: function(){
      if(confirm("삭제후에는 복구가 불가능합니다. 삭제하시겠습니까?")){
      this.$http.delete(`/api/application`,{data:this.application})
      .then(response => {
        if(response.data.result === 'success'){
          alert("삭제되었습니다.")
          this.$router.push(`/dashboard/application`)
        }
        })
      }
    }
  },
  computed:{

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
