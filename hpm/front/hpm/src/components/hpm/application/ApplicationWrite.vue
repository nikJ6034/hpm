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
              <b-row>
                <b-col>
                  <ag-grid-vue
                    :column-defs="columns"
                    :default-col-def="defaultColDef"
                    :grid-options="gridOptions"
                    :row-data="testData"
                    :framework-components="frameworkComponents"
                    class="ag-theme-alpine"
                    style="height: 500px;"
                    @grid-ready="onGridReady" />
                </b-col>
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
            </b-container>
          </b-col>
        </b-row>
      </b-container >
    </v-layout>
  </v-container>
</template>

<script>
import 'ag-grid-community/dist/styles/ag-grid.css'
import 'ag-grid-community/dist/styles/ag-theme-alpine.css'
import { AgGridVue } from 'ag-grid-vue'
import '@ag-grid-community/all-modules/dist/styles/ag-grid.css'
import '@ag-grid-community/all-modules/dist/styles/ag-theme-alpine.css'
import companis from '@/testdata/company'
import instrumentCompany from '@/testdata/instrumentCompany'

import SelectEditor from '@/components/agGrid/selectEditor.js'
import SelectRenderer from '@/components/agGrid/selectRenderer.js'
import NumericEditorVue from '@/components/agGrid/numericEditorVue.js'
import NumericEditor from '@/components/agGrid/numericEditor.vue'

export default {
  components: {
    AgGridVue
  },
  data: () => ({
    defaultColDef: {
      editable: true
    },
    gridOptions: null,
    gridApi: null,
    columns: [ { field: 'name',
                  headerName: '사업자 이름',
                  cellRenderer: 'selectRenderer',
                  cellEditor: 'selectEditor',
                  editable: true,
                  cellEditorParams: {
                    values: instrumentCompany
                  }
              },
                { field: 'instrumentName', headerName: '계측기 이름' },
                { field: 'quantity', headerName: '수량' },
                { fieId: 'regDt', headerName: '작성일' }
            ],
    frameworkComponents: {
      selectRenderer: SelectRenderer,
      selectEditor: SelectEditor,
      numericEditor: NumericEditorVue,
      numberEditor: NumericEditor
    },
    testData: [],
    companis,
    company: { id: null, name: null },
    newLine: {
                name: null,
                instrumentName: null,
                quantity: 0
              }
  }),
  beforeMount () {
    this.gridOptions = { undoRedoCellEditing: true, rowSelection: 'single', undoRedoCellEditingLimit: 20 }
    this.testData = [
        // for rowData prop
        {
          name: 1,
          instrumentName: '계측기1',
          quantity: '1',
          regDt: '2020-10-12'
        },
        {
          name: 1,
          instrumentName: '계측기2',
          quantity: '2',
          regDt: '2020-10-12'
        }
      ]
  },
  mounted () {
     this.gridApi = this.gridOptions.api
     this.gridColumnApi = this.gridOptions.columnApi
   },
  methods: {
    onGridReady (params) {
      console.log(1111)
    },
    companyClick: function (item) {
      this.company.id = item.id
      this.company.name = item.name
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
      this.gridApi.updateRowData({ add: [this.newLine] })
    },
    rowDelete () {
      const selectedRows = this.gridApi.getSelectedRows()
      this.gridApi.updateRowData({ remove: selectedRows })
    }
  }
}
</script>

<style>
</style>
