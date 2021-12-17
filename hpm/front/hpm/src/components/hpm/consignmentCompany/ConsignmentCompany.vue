<template>
  <div>
    <base-header type="gradient-success" class="pb-6 pt-1">
    </base-header>
    <b-container
      class="my-2"
      fluid>
      <b-row
        class="text-center"
        style="padding: 0 0 0 10px;">
        <b-col
          xl="5"
          style="padding:0 5px 0 5px">
          <b-container>
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
                  type="text"
                  v-model="keyword"
                  v-on:keydown.enter="search()"
                  placeholder="검색조건을 입력해주세요." />
              </b-col>
              <b-col
                cols="3"
                sm="3">
                <b-button
                  block
                  variant="outline-primary"
                  @click="search">
                  조회
                </b-button>
              </b-col>
            </b-row>
            <b-row>
              <b-table
                style="font-size: 12px"
                :items="consignmentCompanise"
                :fields="fields"
                class="pointer"
                hover
                @row-clicked="itemClick"
              />
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
          xl="7">
          <b-container
            style="height: 750px;">
            <b-row>
              <b-col
                v-if="consignmentCompany.id == null"
                class="f25">
                위탁업체 등록
              </b-col>
              <b-col
                v-else
                class="f25">
                위탁업체 정보
              </b-col>
            </b-row>
            <hr>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                위탁업체명
              </b-col>
              <b-col
                cols="9">
                <b-form-input
                  v-model="consignmentCompany.name"
                  placeholder="위탁업체명을 입력하세요." />
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                전화번호
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.tel"
                  placeholder="전화번호를 입력하세요." />
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                팩스번호
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.fax"
                  placeholder="팩스번호를 입력하세요." />
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                주소
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.postNumber"
                  style="width:150px;"
                  class="float-left mr-1"
                  readonly="readonly"
                  placeholder="우편번호" />
                <b-button
                  class="float-left mr-1"
                  variant="outline-primary"
                  @click="postPopupOpen"
                >주소검색</b-button>
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right" />
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.adress"
                  placeholder="주소를 입력하세요.(도로명)" />
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                상세주소
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.adressDetail"
                  placeholder="상세주소를 입력하세요." />
              </b-col>
            </b-row>
            
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                담당자 이름
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.picName"/>
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                담당자 전화번호
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.picTel"/>
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                HPM 담당자
              </b-col>
              <b-col cols="9">
                <b-form-input
                  v-model="consignmentCompany.hpmPicName"/>
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col
                cols="3"
                class="mFormLbl text-right">
                기타
              </b-col>
              <b-col cols="9">
                <b-form-textarea
                  v-model="consignmentCompany.etc"/>
              </b-col>
            </b-row>
            <b-row
              class="mt-1">
              <b-col>
                <b-button
                  v-if="consignmentCompany.id != null"
                  class="float-right mr-1"
                  variant="outline-primary"
                  @click="cancel">취소</b-button>
                <b-button
                  v-if="consignmentCompany.id == null"
                  class="float-right mr-1"
                  variant="outline-primary"
                  @click="register"
                >등록</b-button>
                <b-button
                  v-if="consignmentCompany.id != null"
                  class="float-right mr-1"
                  variant="outline-primary"
                  @click="modify">수정</b-button>
                <b-button
                  v-if="consignmentCompany.id != null"
                  class="float-right mr-1"
                  variant="outline-primary"
                  @click="remove"
                >삭제</b-button>
              </b-col>
            </b-row>
          </b-container>
        </b-col>
      </b-row>
    </b-container >
  </div>
</template>

<script>

export default {
  data: () => ({
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'picName', label: '담당자' }, { key: 'picTel', label: '담당자 전화번호' }, { key: 'hpmPicName', label: 'HPM 담당자' } ],
    consignmentCompanise: [],
    currentPage: 1,
    perPage: 10,
    rows: 0,
    keyword: '',
    condition: '',
    consignmentCompany: { id: null, name: null, tel: null, fax: null, postNumber: null, adress: null, addressJibun: null, adressDetail: null, etc: null, picName: null, picTel: null, hpmPicName: null }
  }),
  beforeMount () {
    this.search()
  },
  methods: {
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
    itemClick: function (item) {
      this.consignmentCompany = JSON.parse(JSON.stringify(item))
    },
    register: function () {
      if (this.consignmentCompany.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (confirm('위탁업체를 등록하시겠습니까?')) {
      this.$http.post(`/api/company`, this.consignmentCompany)
      .then(response => {
        if (response.data.result === 'success') {
          alert('위탁업체가 등록되었습니다.')
          this.search(this.currentPage)
          this.cancel()
        }
        })
      }
    },
    modify: function () {
      if (this.consignmentCompany.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (confirm('위탁업체 정보를 수정하시겠습니까?')) {
        this.$http.put(`/api/company`, this.consignmentCompany)
        .then(response => {
          if (response.data.result === 'success') {
            alert('위탁업체 정보가 변경되었습니다.')
            this.search(this.currentPage)
          }
          })
      }
    },
    remove () {
      if (confirm('정말 위탁업체를 삭제하시겠습니까?')) {
      this.$http.delete(`/api/company`, { data: this.consignmentCompany })
      .then(response => {
        if (response.data.result === 'success') {
          alert('위탁업체가 삭제되었습니다.')
          this.search(this.currentPage)
          this.cancel()
        }
        })
      }
    },
    cancel: function () {
      this.consignmentCompany = { id: null, name: null, tel: null, fax: null, postNumber: null, adress: null, adressJibun: null, adressDetail: null, etc: null }
    },
    postPopupOpen: function () {
      const _this = this
      new window.daum.Postcode({
          oncomplete: function (data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
              // 예제를 참고하여 다양한 활용법을 확인해 보세요.
              _this.consignmentCompany.postNumber = data.zonecode
              _this.consignmentCompany.adress = data.roadAddress
              _this.consignmentCompany.addressJibun = data.jibunAddress
          }
      }).open()
    }
  }
}
</script>

<style>
.mFormLbl { line-height: 2.3em; font-weight: bold;}
.f25 {font-size: 25px; text-align: left; font-weight: bold;}
.pointer {cursor: pointer;}
</style>
