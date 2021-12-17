<template>
  <b-container>
    <b-row>
      <b-col
        cols="3"
        sm="3"
        class="pr-0">
        <b-form-select
          v-model="condition"
          class="mb-3">
          <b-form-select-option value="">전체</b-form-select-option>
          <b-form-select-option value="name">거래처 이름</b-form-select-option>
          <b-form-select-option value="tel">전화번호</b-form-select-option>
          <b-form-select-option value="companyRegNumber">사업자번호</b-form-select-option>
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
          v-on:keydown.enter="search()"
          placeholder="검색조건을 입력해주세요." />
      </b-col>
      <b-col
        cols="3"
        sm="2">
        <b-button
          block
          variant="outline-primary"
          @click="search()">조회</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-table
        style="font-size: 12px"
        :items="customers"
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
    <b-row class="d-block">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        align="center"
        @page-click="pageSearch"
      />
    </b-row>
  </b-container>
</template>

<script>
export default {
  data: () => ({
      fields: [{ key: 'index', label: 'No.', thStyle: { width: '5%'}  }
              , { key: 'name', label: '거래처명', thStyle: { width: '15%'}  }
              , { key: 'companyRegNumber', label: '사업자번호', thStyle: { width: '15%'}  }
              , { key: 'picName', label: '담당자', thStyle: { width: '25%'}  }
              , { key: 'tel', label: '전화번호', thStyle: { width: '20%'}  }],
      customers: [],
      currentPage: 1,
      perPage: 10,
      rows: 0,
      keyword: '',
      condition: ''
    }),
  beforeMount () {
    this.search()
  },
  methods: {
    search (num) {
      const page = num - 1 | 0
      this.$http.get(`/api/customer?page=${page}&keyword=${this.keyword}&condition=${this.condition}`)
      .then(response => {
        this.customers = response.data.content
        this.rows = response.data.totalElements
        this.perPage = response.data.size
        this.currentPage = response.data.number + 1
        })
    },
    pageSearch (bvEvent, page) {
      this.search(page)
    },
    itemClick (data){
      this.$emit("customer",data)
    }
  }
}
</script>

<style>

</style>