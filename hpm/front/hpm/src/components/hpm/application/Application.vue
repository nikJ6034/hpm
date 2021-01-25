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
          style="padding: 0 0 0 10px;">
          <b-col
            xl="6"
            style="padding:0 5px 0 5px">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">거래처 목록</b-col>
              </b-row>
              <hr>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    v-model="condition"
                    class="mb-3">
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="name">거래처명</b-form-select-option>
                    <b-form-select-option value="companyRegNumber">사업자번호</b-form-select-option>
                    <b-form-select-option value="tel">전화번호</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="customerName"
                    v-model="keyword"
                    type="text"
                    placeholder="키워드를 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    variant="outline-primary"
                    @click="customerSearch">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="customers"
                  :fields="fields"
                  @row-clicked="customerClick" >
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
              <b-row>
                <b-col>
                  <b-button
                    v-if="customer.id != null"
                    class="float-right mr-1"
                    variant="outline-danger"
                    @click="selectCancel">선택취소</b-button>
                  <b-button
                    v-if="customer.id != null"
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="goApplicationWritePage">신청서 작성</b-button>
                </b-col>
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="6"
            style="padding:0 5px 0 0; border-left:1px solid lightgrey;">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">신청서 목록</b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="applicationList"
                  :fields="appFields"
                  @row-clicked="appClick" >
                  <template #cell(index)="data">
                    {{ data.index + 1 }}
                  </template>
                </b-table>
              </b-row>
              <b-row class="d-inline-block">
                <b-pagination
                  v-model="appCurrentPage"
                  :total-rows="appRows"
                  :per-page="appPerPage"
                  align="center"
                  @page-click="appPageSearch"
                />
              </b-row>
              <b-row class="mt-1">
                <b-col>
                  <b-button
                    class="float-right mr-1"
                    variant="outline-primary">수정</b-button>
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

export default {
  data: () => ({
    fields: [ { key: 'index', label: 'No' }, { key: 'name', label: '거래처명' }, { key: 'companyRegNumber', label: '사업자 번호' }, { key: 'tel', label: '전화번호' } ],
    appFields: [ { key: 'index', label: 'No' }, { key: 'customer.name', label: '거래처명' }, { key: 'regDate', label: '신청일' } ],
    customers: null,
    customer: { id: null, name: null },
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition: '',
    applicationList: [],
    appPerPage: 10,
    appCurrentPage: 1,
    appRows: 0
  }),
  beforeMount () {
      this.customerSearch()
      this.applicationSearch()
  },
  methods: {
    customerClick: function (item) {
      this.customer = item
      this.applicationSearch()
    },
    selectCancel: function () {
      this.customer = { id: null, name: null }
      this.applicationSearch()
    },
    goApplicationWritePage: function () {
      if (this.customer.id == null) {
        alert('사업자를 선택해주세요.')
        return false
      } else {
        this.$router.push(`/dashboard/application/write?customerId=${this.customer.id}`)
      }
    },
    customerSearch: function (num) {
      const page = num - 1 || 0
      this.$http.get(`/api/customer?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.customers = response.data.content
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number + 1
        })
    },
    applicationSearch: function (num) {
      const page = num - 1 || 0
      const customer = this.customer.id || 0
      this.$http.get(`/api/application?page=${page}&customer=${customer}`)
      .then(response => {
        this.applicationList = response.data.content
        this.appRows = response.data.totalElements
        this.appPerPage = response.data.size
        this.appcurrentPage = response.data.number + 1
        })
    },
    appPageSearch (bvEvent, page) {
      this.applicationSearch(page)
    },
    pageSearch (bvEvent, page) {
      this.customerSearch(page)
    },
    appClick (item) {
      this.$router.push(`/dashboard/application/write?id=${item.id}`)
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
