<template>
    <div>
        <base-header class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center">
            <!-- Mask -->
            <span class="mask bg-gradient-success opacity-8"></span>
            <!-- Header container -->
            
        </base-header>

        <div class="container-fluid mt--7">
            <div class="row">
                
                <div class="col-xl-12 order-xl-1">
                    <card >
                        
                        <template>
                            <form @submit.prevent>
                                <h6 class="heading-small text-muted mb-4">회원정보</h6>
                                <div class="pl-lg-4">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="ID"
                                                        placeholder="ID"
                                                        readonly="readonly"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.memberId"
                                            />
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="이름"
                                                        placeholder="이름"
                                                        readonly="readonly"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.name"
                                            />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="이메일 주소"
                                                        placeholder="jesse@example.com"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.email"
                                            />
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="전화번호"
                                                        placeholder="전화번호"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.mobile"
                                            />
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="비밀번호"
                                                        placeholder="비밀번호"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.password"
                                            />
                                        </div>
                                        <div class="col-lg-6">
                                            <base-input alternative=""
                                                        label="비밀번호 확인"
                                                        placeholder="비밀번호 확인"
                                                        input-classes="form-control-alternative"
                                                        v-model="userInfo.passwordconfirm"
                                            />
                                        </div>
                                    </div>

                                    <b-button
                                    class="float-right mr-1"
                                    variant="outline-primary"
                                    size="sm"
                                    @click="modifyUserInfo()"
                                    >수정</b-button>
                                </div>
                            
                                <!-- <hr class="my-4" />
                                <h6 class="heading-small text-muted mb-4">비밀번호 변경</h6>
                                <div class="pl-lg-4">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <base-input alternative=""
                                                        label="Address"
                                                        placeholder="Home Address"
                                                        input-classes="form-control-alternative"
                                                        v-model="model.address"
                                            />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <base-input alternative=""
                                                        label="City"
                                                        placeholder="City"
                                                        input-classes="form-control-alternative"
                                                        v-model="model.city"
                                            />
                                        </div>
                                        <div class="col-lg-4">
                                            <base-input alternative=""
                                                        label="Country"
                                                        placeholder="Country"
                                                        input-classes="form-control-alternative"
                                                        v-model="model.country"
                                            />
                                        </div>
                                        <div class="col-lg-4">
                                            <base-input alternative=""
                                                        label="Postal code"
                                                        placeholder="Postal code"
                                                        input-classes="form-control-alternative"
                                                        v-model="model.zipCode"
                                            />
                                        </div>
                                    </div>
                                </div> -->
                            </form>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  export default {
    name: 'user-profile',
    data() {
      return {
        userInfo: {
            memberId:null,
            mobile:null,
            name:null,
            email:null,
            password:null,
            passwordconfirm:null
        }
      }
    },
    beforeMount () {
      this.getUserInfo()
    },
    methods: {
        modifyUserInfo : function(){
            alert(111)
        },
        getUserInfo : function(){
            this.$http.get(`/api/userInfo`)
            .then(response => {
                console.log(this)
                this.userInfo = response.data
            })
            
        }
    }
  };
</script>
<style></style>
