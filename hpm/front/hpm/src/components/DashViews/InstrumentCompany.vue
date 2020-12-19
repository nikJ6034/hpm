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
                  <b-form-select class="mb-3">
                    <b-form-select-option
                      :value="null"
                      disabled>선택해주세요.</b-form-select-option>
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
                  :items="instrumentCompanis"
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
                <h2 style="color:#000000">사업자 상세정보</h2>
              </b-row>
              <b-row class="mt-1">
                <b-col cols="3">
                  <div>사업자 이름</div>
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="instrumentCompany.name"
                    placeholder="사업자 이름을 입력하세요." />
                </b-col>
              </b-row>
              <b-row class="mt-1">
                <b-col cols="3">
                  사업자 번호
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="instrumentCompany.number"
                    placeholder="사업자 번호를 입력하세요." />
                </b-col>
              </b-row>
              <b-row class="mt-1">
                <b-col cols="3">
                  사업자 주소
                </b-col>
                <b-col cols="9">
                  <b-form-input
                    v-model="instrumentCompany.address"
                    placeholder="사업자 주소를 입력하세요." />
                </b-col>
              </b-row>
              <b-row class="mt-1">
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
import instrumentCompanis from '../../testdata/instrumentCompany'

export default {
  data: () => ({
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'address', label: '새주소' }, { key: 'number', label: '사업자 번호' } ],
    instrumentCompanis,
    test: '1111',
    instrumentCompany: { id: null, name: null, postCode: null, address: null, address2: null, number: null }
  }),
  methods: {
    companyClick: function (item) {
      this.instrumentCompany.id = item.id
      this.instrumentCompany.name = item.name
      this.instrumentCompany.postCode = item.postCode
      this.instrumentCompany.address = item.address
      this.instrumentCompany.address2 = item.address2
      this.instrumentCompany.number = item.number
    },
    companyModify () {
      const _this = this
      if (this.instrumentCompany.name == null) {
        alert('이름을 입력해주세요.')
        return false
      }

      if (this.instrumentCompany.id == null) {
        this.instrumentCompany.push(this.instrumentCompany)
      } else {
        this.instrumentCompanis.some(function (item) {
          if (item.id === _this.instrumentCompany.id) {
            item.name = _this.instrumentCompany.name
            item.postCode = _this.instrumentCompany.postCode
            item.address = _this.instrumentCompany.address
            item.address2 = _this.instrumentCompany.address2
            item.number = _this.instrumentCompany.number
          }
        })
      }
    }
  }
}
</script>

<style>

</style>
