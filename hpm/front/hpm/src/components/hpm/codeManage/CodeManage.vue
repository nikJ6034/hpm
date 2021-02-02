<template>
  <v-container
    fill-height
    fluid
    grid-list-xl>
    <v-layout
      justify-center
      wrap>
      <b-container fluid>
        <b-row
          class="text-center"
          style="padding: 0 0 0 10px;">
          <b-col
            xl="6"
            style="padding:0 5px 0 5px">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">코드 목록</b-col>
              </b-row>
              <hr>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    v-model="condition"
                    class="mb-3"
                  >
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="code">분류코드</b-form-select-option>
                    <b-form-select-option value="codeName">분류코드명</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    type="text"
                    placeholder="검색조건을 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    variant="outline-primary"
                    @click="search">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="codes"
                  :fields="fields"
                  class="pointer"
                  hover
                  @row-clicked="itemClick"
                >
                  <template #cell(index)="data">
                    {{ data.index + 1 }}
                  </template>
                </b-table>
              </b-row>
              <b-row class="d-inline-block">
                <b-pagination
                  v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  align="center"
                  @page-click="pageSearch"
                />
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="6"
            style="padding:0 5px 0 0; border-left:1px solid lightgrey;">
            <b-container
              style="height: 450px;">
              <b-row>
                <b-col
                  v-if="code.id == null"
                  class="f25"
                >코드 등록</b-col>
                <b-col
                  v-else
                  class="f25"
                >코드 정보</b-col>
              </b-row>
              <hr>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  분류코드
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    :readonly="code.id != null"
                    v-model="code.code"
                    placeholder="분류코드을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">
                  분류코드명
                </b-col>
                <b-col
                  cols="10">
                  <b-form-input
                    :readonly="code.id != null"
                    v-model="code.codeName"
                    placeholder="분류코드명을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="2"
                  class="mFormLbl text-right">설명
                </b-col>
                <b-col cols="10">
                  <b-form-textarea
                    v-model="code.codeDesc"
                    rows="3"
                  />
                </b-col>
              </b-row>

              <hr>
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
                <b-col style="height:300px">
                  <grid
                    ref="tuiGrid"
                    :data="testData"
                    :columns="columns"
                    :options="gridOptions"
                    language="ko"
                    @editingFinish="rowChange"
                  />
                </b-col>
              </b-row>

              <b-row
                class="mt-1">
                <b-col>
                  <b-button
                    v-if="code.id == null"
                    class="float-right"
                    variant="outline-primary"
                    @click="register"
                  >등록</b-button>

                  <b-button
                    v-if="code.id != null"
                    class="float-right"
                    variant="outline-primary"
                    @click="cancel"
                  >취소</b-button>
                  <b-button
                    v-if="code.id != null"
                    class="float-right mr-3"
                    variant="outline-danger"
                    @click="remove"
                  >삭제</b-button>
                  <b-button
                    v-if="code.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="modify">수정</b-button>
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
    gridOptions: { bodyHeight: 'fitToParent', rowHeaders: ['checkbox'] },
    columns: [
                { header: '코드', name: 'code', editor: 'text' },
                { header: '코드명', name: 'codeName', editor: 'text' },
                { header: '설명', name: 'codeDesc', editor: 'text' }
            ],
    testData: [],
    fields: [{ key: 'index', label: 'No.' }, { key: 'code', label: '분류코드' }, { key: 'codeName', label: '분류코드명' }],
    codes: [],
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition: '',
    code: { id: null, code: null, codeName: null, codeDesc: null, edite: null, pid: null, del: null, codeList: [] },
    deleteChildCode: []
  }),
  beforeMount () {
    this.search()
  },
  methods: {
    search (num) {
      const page = num - 1 | 0
      this.$http.get(`/api/code?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.codes = response.data.content
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number + 1
        })
    },
    pageSearch (bvEvent, page) {
      this.search(page)
    },
    itemClick: function (item) {
      this.$http.get(`/api/code/${item.id}`)
      .then(response => {
        this.code = response.data
        this.$refs.tuiGrid.invoke('resetData', this.code.codeList)
        })
    },
    register: function () {
      if (this.code.codeName == null) {
        alert('코드명을 입력해주세요.')
        return false
      }
      if (this.code.code == null) {
        alert('코드를 입력해주세요.')
        return false
      }
      this.$refs.tuiGrid.invoke('finishEditing')
      this.code.codeList = [...this.$refs.tuiGrid.invoke('getData'), ...this.deleteChildCode]
      if (confirm('코드를 등록하시겠습니까?')) {
      this.$http.post(`/api/code`, this.code)
      .then(response => {
        if (response.data.result === 'success') {
          alert(response.data.msg)
          this.search(this.currentPage)
          this.code = response.data.code
          this.itemClick(this.code)
        } else {
          alert(response.data.msg)
        }
        })
      }
    },
    modify () {
      if (this.code.codeName == null) {
        alert('코드명을 입력해주세요.')
        return false
      }
      if (this.code.code == null) {
        alert('코드를 입력해주세요.')
        return false
      }
      this.$refs.tuiGrid.invoke('finishEditing')
      this.code.codeList = [...this.$refs.tuiGrid.invoke('getData'), ...this.deleteChildCode]
      if (confirm('코드를 수정하시겠습니까?')) {
      this.$http.put(`/api/code`, this.code)
      .then(response => {
        if (response.data.result === 'success') {
          alert(response.data.msg)
          this.search(this.currentPage)
          this.itemClick(this.code)
        } else {
          alert(response.data.msg)
        }
        })
      }
    },
    remove () {
      if (confirm('부모코드를 삭제하시면 자식코드도 같이 삭제되며 복구되지 않습니다.\n코드를 삭제하시겠습니까?')) {
      this.$http.delete(`/api/code`, { data: this.code })
      .then(response => {
        if (response.data.result === 'success') {
          alert('코드가 삭제되었습니다.')
          this.search(this.currentPage)
          this.cancel()
        }
        })
      }
    },
     cancel: function () {
      this.code = { id: null, code: null, codeName: null, codeDesc: null, edite: null, pid: null, del: null, codeList: [] }
      this.$refs.tuiGrid.invoke('resetData', this.code.codeList)
    },
    rowAdd: function () {
      const childCode = { id: null, code: null, codeName: null, codeDesc: '', edite: null, pid: null, del: null }
      this.$refs.tuiGrid.invoke('appendRow', childCode)
    },
    rowDelete () {
      const _this = this
      const keys = this.$refs.tuiGrid.invoke('getCheckedRowKeys')
      keys.forEach(function (key) {
        const childCode = _this.$refs.tuiGrid.invoke('getRow', key)
        if (childCode.id) {
          childCode.del = true
          _this.deleteChildCode.push(childCode)
        }
        _this.$refs.tuiGrid.invoke('removeRow', key)
      })
    },
    rowChange: function (data) {
      const rowkey = data.rowKey
      this.$refs.tuiGrid.invoke('setValue', rowkey, 'edite', true)
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
