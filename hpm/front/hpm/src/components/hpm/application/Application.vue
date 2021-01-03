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
          style="padding: 0 10px 0 10px;">
          <b-col
            xl="5"
            style="padding:0 5px 0 5px">
            <b-container
              style="background-color:#f5f4f4; border-radius: 15px; height: 750px;">
              <b-row>
                <h2
                  style="color:#000000">거래처 정보</h2>
              </b-row>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select class="mb-3" v-model="condition">
                    <b-form-select-option value="">전체</b-form-select-option>
                    <b-form-select-option value="name">거래처명</b-form-select-option>
                    <b-form-select-option value="tel">전화번호</b-form-select-option>
                    <b-form-select-option value="companyRegNumber">사업자번호</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    v-model="keyword"
                    id="customerName"
                    type="text"
                    placeholder="키워드를 입력해주세요." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    @click="customerSearch"
                    variant="outline-primary">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="customers"
                  :fields="fields"
                  @row-clicked="customerClick" />
              </b-row>
              <b-row class="d-inline-block">
                <b-pagination
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    @page-click="pageSearch"
                    align="center"
                  ></b-pagination>
              </b-row>
              <b-row>
                <b-col>
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
            xl="7"
            style="padding:0 5px 0 5px">
            <b-container
              style="background-color:#f5f4f4; border-radius: 15px; height: 750px;">
              <b-row>
                <h2 style="color:#000000">신청서 목록</h2>
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
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'companyRegNumber', label: '사업자 번호' }, { key: 'tel', label: '전화번호' } ],
    customers: null,
    customer: { id: null, name: null },
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition:'',
  }),
  beforeMount () {
      this.customerSearch()
  },
  methods: {
    customerClick: function (item) {
      this.customer.id = item.id
      this.customer.name = item.name
    },
    goApplicationWritePage: function () {
      if (this.customer.id == null) {
        alert('사업자를 선택해주세요.')
        return false
      } else {
        this.$router.push(`/dashboard/application/write?customerId=${this.customer.id}`)
      }
    },
    customerSearch: function (num){
      const page = num-1|0;
      this.$http.get(`/api/customer?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.customers = response.data.content;
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number+1
        })
    },
    pageSearch(bvEvent, page){
      this.customerSearch(page);
    }
  }
}
</script>

<style>

</style>
