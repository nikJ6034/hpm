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
              style="height: 750px;">
              <b-row>
                <b-col class="f25">신청서 목록</b-col>
              </b-row>

              <hr/>

              <b-row class="y-scroll">
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
                      <b-td class="text-left">삼성</b-td>
                      <b-th class="text-center">대표자</b-th>
                      <b-td class="text-left" colspan="3">이재용</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">사업자등록번호</b-th>
                      <b-td class="text-left">123-123-12345</b-td>
                      <b-th class="text-center">업태</b-th>
                      <b-td class="text-left">50</b-td>
                      <b-th class="text-center">종목</b-th>
                      <b-td class="text-left">15</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">주소</b-th>
                      <b-td class="text-left" colspan="5">51</b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th class="text-center">전화번호</b-th>
                      <b-td class="text-left">114</b-td>
                      <b-th class="text-center">팩스번호</b-th>
                      <b-td class="text-left" colspan="3">114</b-td>
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
                      <b-th rowspan="2" class="text-center">성적서 발행<br>업체명/주소</b-th>
                      <b-td  rowspan="2" class="text-center" >
                        <b-form-group v-slot="{ ariaDescribedby }">
                          <b-form-radio-group
                            v-model="selected"
                            :options="options"
                            :aria-describedby="ariaDescribedby"
                            name="radios-stacked"
                            stacked
                          ></b-form-radio-group>
                        </b-form-group>
                      </b-td>
                      <b-th>업체명</b-th>
                      <b-td>
                        <b-form-input
                        placeholder="업체명을 입력하세요." />
                      </b-td>
                    </b-tr>
                    <b-tr class="trBgW">
                      <b-th>주소</b-th>
                      <b-td>
                        <b-form-input
                          placeholder="주소를 입력하세요." />
                      </b-td>
                    </b-tr>
                  </b-tbody>
                </b-table-simple>

                <b-table-simple 
                  small 
                  responsive 
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
                          v-model="status"
                          name="checkbox-1"
                          value="accepted"
                          unchecked-value="not_accepted"
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
                          v-model="status"
                          name="checkbox-2"
                          value="accepted"
                          unchecked-value="not_accepted"
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
                            v-model="selected2"
                            :options="options2"
                            name="radios-stacked2"
                            class="fl mr-1"
                          ></b-form-radio-group>

                          <b-form-input
                            id="example-input"
                            v-model="value"
                            type="text"
                            placeholder="YYYY-MM-DD"
                            autocomplete="off"
                            size="sm"
                            class="fl"
                            style="width:120px;"
                          ></b-form-input>
                          <b-form-datepicker
                              v-model="value"
                              button-only
                              locale="en-US"
                              aria-controls="example-input"
                              @context="onContext"
                              size="sm"
                              class="fl"
                            ></b-form-datepicker>
                      </b-td>
                      <b-th>신청인<br>(핸드폰번호)</b-th>
                      <b-td>
                        <b-form-input
                          placeholder="신청인(핸드폰번호)를 입력하세요." />
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
                          v-model="selected3"
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
                      <b-icon class="float-right pointer"
                        icon="file-plus-fill"
                        @click="rowAdd"
                        variant="primary"
                        style="height:31px; width:31px;"
                      ></b-icon>

                      <b-icon class="float-right pointer"
                        icon="file-minus"
                        @click="rowDelete"
                        variant="primary"
                        style="height:31px; width:31px;"
                      ></b-icon>
                    </b-col>
                  </b-row>
                
                  <b-row class="mt-1">
                    <b-col style="height:400px">
                      <grid ref="tuiGrid" :data="testData" :columns="columns" language="ko" :options="gridOptions"/>
                    </b-col>
                  </b-row>

                  <b-row class="mt-2">
                    <b-col>
                      <b-button
                        class="float-right mr-1"
                        variant="outline-primary"
                        size="sm"
                      >접수</b-button>

                      <b-button
                        class="float-right"
                        variant="outline-primary"
                        size="sm"
                      >수정</b-button>

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
    gridOptions: {bodyHeight:'fitToParent',rowHeaders:['checkbox']},
    testData: [],
    companis: [],
    uploadFileImg: null,
    customer: { id: null, name: null },
    newLine: {
                name: null,
                instrumentName: null,
                quantity: 0
              },
    selected : '상동',
    options: [
      { text: '상동', value: '상동' },
      { text: '다름', value: '다름' }
    ],
    selected2 : '택배',
    options2: [
      { text: '방문', value: '방문' },
      { text: '택배', value: '택배' },
      { text: '반출', value: '반출' }
    ],
    selected3 : '직접전달',
    options3: [
      { text: '직접전달', value: '직접전달' },
      { text: '고객방문', value: '고객방문' },
      { text: '택배', value: '택배' }
    ]
  }),
  async created () {
    const _this = this;
    this.columns = [
        {  header: '사업자', name:"consignmentCompany",
          formatter: 'listItemText',
          editor: {
            type: 'select',
            options: {
              listItems: []
            }
          }
        },
        { header: '기기명', name:"deviceName", editor: 'text' },
        { header: '수량', name:"quantity", editor: 'text'  },
        { header: '성적서 번호', name:"reportNumber", editor: 'text'   },
        { header: '제작 회사', name:"productionCompany", editor: 'text'   },
        { header: '기기번호', name:"deviceNumber", editor: 'text'   },
        { header: '규격', name:"standard", editor: 'text'   },
        { header: '단위', name:"unit", editor: 'text'   },
        { header: '교정일자', name:"correctionDate", editor: 'text'   },
        { header: '장소', name:"place", editor: 'text'   },
        { header: '실무자', name:"practitioner", editor: 'text'   },
        { header: '중분류', name:"middleCategory", editor: 'text'   },
        { header: '소분류', name:"smallCategory", editor: 'text'   },
        { header: '발행일자', name:"publishedDate", editor: 'text'   },
        { header: '기술책임자', name:"technicalManager", editor: 'text'   },
        { header: '성적서 언어', name:"reportLanguage", editor: 'text'   }
    ]
    await this.companySearch()  
    
    this.testData = []
    this.$refs.tuiGrid.invoke('setColumns', this.columns);
    this.$refs.tuiGrid.invoke('resetData', this.testData);
  },
  beforeMount () {
    this.uploadFileImg = 'https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png';
  },
  methods: {
    goApplicationWritePage: function () {
      if (this.company.id == null) {
        alert('사업자를 선택해주세요.')
        return false
      } else {
        this.$router.push(`/application/write?companyId=${this.company.id}`)
      }
    },
    rowAdd () {
      this.$refs.tuiGrid.invoke('appendRow', {
          name: 3,
          instrumentName: '계측기3',
          quantity: '3',
          regDt: '2020-10-12'
        });
    },
    rowDelete () {
      const _this = this;
      const keys = this.$refs.tuiGrid.invoke('getCheckedRowKeys');
      keys.forEach(function(key){
        _this.$refs.tuiGrid.invoke('removeRow', key);

      })
    },
    companySearch: async function (){
      await this.$http.get(`/api/companyAll`)
      .then(response => {
        const com = [];
        if(response.data){
          response.data.forEach(function(item){
            com.push({text:item.name, value:item.id+''})
          })
        }
        this.columns[0].editor.options.listItems = com
        //this.companis = com;
        })
      console.log(222);
    },
    fnChangeImage: function (e) {
      const file = e.target.files[0]; // Get first index in files
      this.uploadFileImg = URL.createObjectURL(file); // Create File URL
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
