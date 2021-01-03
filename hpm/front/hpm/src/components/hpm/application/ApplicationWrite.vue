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
              style="background-color:#f5f4f4; border-radius: 15px; height: 750px;">
              <b-row>
                <h2 style="color:#000000">신청서 목록</h2>
              </b-row>
              <b-row class="mt-1">
                <b-col>
                  <!-- <numeric-editor v-model="value"></numeric-editor> -->
                  <!-- <select-editor></select-editor> -->
                  <b-button
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="rowAdd">추가</b-button>
                  <b-button
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="rowDelete">삭제</b-button>
                </b-col>
              </b-row>
             <b-row>
                <b-col style="height:600px">
                  <grid ref="tuiGrid" :data="testData" :columns="columns" language="ko" :options="gridOptions"/>
                </b-col>
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
    customer: { id: null, name: null },
    newLine: {
                name: null,
                instrumentName: null,
                quantity: 0
              }
  }),
  async created () {
    const _this = this;
    this.columns = [ {  header: '사업자', name:"consignmentCompany",
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
    console.log(33);
    
    
    this.testData = [
        
      ]

      this.$refs.tuiGrid.invoke('setColumns', this.columns);
      this.$refs.tuiGrid.invoke('resetData', this.testData);
  },
  beforeMount () {
    
    
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
    }
  }
}
</script>

<style>
</style>
