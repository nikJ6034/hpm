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
            xl="5"
            style="padding:0 5px 0 5px">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">위탁업체 목록</b-col>
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
                    <b-form-select-option value="name">이름</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="6"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    v-model="keyword"
                    type="text"
                    placeholder="검색조건을 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="3">
                  <b-button
                    block
                    variant="outline-primary"
                    @click="search"
                  >조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="consignmentCompanise"
                  :fields="fields"
                  class="pointer"
                  hover
                  @row-clicked="itemClick">
                  <template #cell(app)="row">
                    <b-button
                      size="sm"
                      class="mr-2"
                      @click="appWindowOpen(row.item)">
                      신청서 작성
                    </b-button>
                  </template>
                </b-table>
                <b-modal
                  v-model="modalShow"
                  title="신청서 작성"
                  @ok="appPage"
                  @hide="cancel">
                  시작일 :
                  <b-form-datepicker
                    v-model="startDt"
                    class="mb-2"
                    placeholder="YYYY-MM-DD"/>
                  종료일 :
                  <b-form-datepicker
                    v-model="endDt"
                    class="mb-2"
                    placeholder="YYYY-MM-DD"/>
                </b-modal>
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
                    v-if="consignmentCompany.id != null"
                    variant="outline-danger"
                    class="float-right mr-1"
                    @click="cancel">선택취소</b-button>
                </b-col>
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="7"
            style="padding:0 5px 0 0; border-left:1px solid lightgrey;">
            <b-container
              style="height: 750px;">
              <b-row>
                <b-col class="f25">신청 이력</b-col>
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
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'tel', label: '전화번호' }, { key: 'app', label: '신청서작성' } ],
    appFields: [ { key: 'index', label: 'No' }, { key: 'consignmentCompany.name', label: '거래처명' }, { key: 'startDt', label: '시작일' }, { key: 'endDt', label: '종료일' } ],
    consignmentCompanise: [],
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition: '',
    consignmentCompany: { id: null, name: null, tel: null, fax: null, postNumber: null, adress: null, adressDetail: null, etc: null },
    modalShow: false,
    startDt: '',
    endDt: '',
    applicationList: [],
    appPerPage: 10,
    appCurrentPage: 1,
    appRows: 0
  }),
  beforeMount () {
      this.search()
      this.applicationSearch()
  },
  methods: {
    itemClick: function (item) {
      this.consignmentCompany = item
      this.applicationSearch()
    },
    cancel: function () {
      this.consignmentCompany = { id: null, name: null, tel: null, fax: null, postNumber: null, adress: null, adressDetail: null, etc: null }
      this.startDt = ''
      this.endDt = ''
      this.applicationSearch()
    },
    search (num) {
      const page = num - 1 | 0
      this.$http.get(`/api/company?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.consignmentCompanise = response.data.content
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number + 1
        })
    },
    pageSearch (bvEvent, page) {
      this.search(page)
    },
    applicationSearch: function (num) {
      const page = num - 1 || 0
      const consignmentCompany = this.consignmentCompany.id || 0
      this.$http.get(`/api/companyApp?page=${page}&consignmentCompany.id=${consignmentCompany}`)
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
    appRemove () {
    },
    appWindowOpen (item) {
      this.itemClick(item)
      this.modalShow = true
    },
    appPage (bvModalEvt) {
      if (!this.startDt) {
        alert('시작일을 입력해주세요.')
        bvModalEvt.preventDefault()
        return false
      } else if (!this.startDt) {
        alert('종료일을 입력해주세요.')
        bvModalEvt.preventDefault()
        return false
      } else if (!this.consignmentCompany.id) {
        alert('사업자가 선택되지 않았습니다.')
        bvModalEvt.preventDefault()
        return false
      }
      this.$router.push(`/dashboard/consign/write?companyId=${this.consignmentCompany.id}&startDt=${this.startDt}&endDt=${this.endDt}`)
    },
    appClick (item) {
      this.$router.push(`/dashboard/consign/write?id=${item.id}`)
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
