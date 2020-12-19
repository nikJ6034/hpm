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
            xl="4"
            style="padding:0 5px 0 5px">
            <b-container
              style="background-color:#f5f4f4; border-radius: 15px; height: 750px;">
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
                  :items="companis"
                  :fields="fields"
                  @row-clicked="companyClick" />
              </b-row>
              <b-row>
                <b-col>
                  <b-button
                    class="float-right mr-1"
                    variant="outline-primary"
                    @click="goApplicationWritePage">신청서 작성</b-button>
                </b-col>
              </b-row>
            </b-container>
          </b-col>
          <b-col
            xl="8"
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
import companis from '@/testdata/company'

export default {
  data: () => ({
    fields: [ { key: 'name', label: '사업자 이름' }, { key: 'number', label: '사업자 번호' } ],
    companis,
    company: { id: null, name: null }
  }),
  methods: {
    companyClick: function (item) {
      this.company.id = item.id
      this.company.name = item.name
    },
    goApplicationWritePage: function () {
      if (this.company.id == null) {
        alert('사업자를 선택해주세요.')
        return false
      } else {
        this.$router.push(`/dashboard/application/write?companyId=${this.company.id}`)
      }
    }
  }
}
</script>

<style>

</style>
