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
          style="padding: 0 10px 0 10px;">
          <b-col
            xl="5"
            style="padding:0 5px 0 5px">
            <b-container
              style="background-color:#f5f4f4; border-radius: 15px; height: 350px;">
              <b-row>
                <h2
                  style="color:#000000">사업자 정보</h2>
              </b-row>
              <b-row>
                <b-col
                  cols="3"
                  sm="3"
                  class="pr-0">
                  <b-form-select
                    class="mb-3">
                    <b-form-select-option value="">선택해주세요.</b-form-select-option>
                  </b-form-select>
                </b-col>
                <b-col
                  cols="6"
                  sm="7"
                  class="pr-0">
                  <b-form-input
                    id="companyName"
                    type="text"
                    placeholder="아직 구현되지 않았습니다." />
                </b-col>
                <b-col
                  cols="3"
                  sm="2">
                  <b-button
                    block
                    variant="outline-primary">조회</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-table
                  :items="companis"
                  :fields="fields"
                  @row-clicked="companyClick" />
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="7"
            style="padding:0 5px 0 5px">
            <b-container
              style="background-color:#f5f4f4; border-radius: 15px; height: 750px;">
              <b-row>
                <h2
                  style="color:#000000">사업자 상세정보</h2>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3">
                  <div>사업자 이름</div>
                </b-col>
                <b-col
                  cols="9">
                  <b-form-input
                    v-model="company.name"
                    placeholder="사업자 이름을 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3">
                  사업자 번호
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="company.number"
                    placeholder="사업자 번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col
                  cols="3">
                  사업자 주소
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="company.address"
                    placeholder="사업자 주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row
                class="mt-1">
                <b-col>
                  <b-button
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="companyModify">수정</b-button>
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
import companis from '../../testdata/company'

export default {
  data: () => ({
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'address', label: '새주소' }, { key: 'number', label: '사업자 번호' } ],
    companis,
    test: '1111',
    company: { id: null, name: null, postCode: null, address: null, address2: null, number: null }
  }),
  methods: {
    companyClick: function (item) {
      this.company.id = item.id
      this.company.name = item.name
      this.company.postCode = item.postCode
      this.company.address = item.address
      this.company.address2 = item.address2
      this.company.number = item.number
    },
    companyModify () {
      const _this = this
      if (this.company.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (this.company.id == null) {
        this.companis.push(this.company)
      } else {
        this.companis.some(function (item) {
          if (item.id === _this.company.id) {
            item.name = _this.company.name
            item.postCode = _this.company.postCode
            item.address = _this.company.address
            item.address2 = _this.company.address2
            item.number = _this.company.number
          }
        })
      }
    }
  }
}
</script>

<style>

</style>
