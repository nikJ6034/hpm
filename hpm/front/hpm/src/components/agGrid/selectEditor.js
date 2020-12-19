import Vue from 'vue'

export default Vue.extend({
  data () {
    return {
      selected: null,
      text: null,
      values: []
    }
  },
  created () {
    this.selected = this.params.value
    this.values = this.params.values
  },
  mounted () {
    // Vue.nextTick(() => {
    //   Vue.$refs.container.focus()
    // })
  },
  methods: {
    getValue () {
      return this.selected
    },
    isPopup () {
      return false
    },
    setValue (val) {
      this.selected = val
      this.params.api.stopEditing()
    }
  },
  template: `
  <div>
    <b-form-select @change="setValue($event)" v-model="selected" :options="values" value-field="id"
    text-field="name"></b-form-select>
  </div>
  `
})
