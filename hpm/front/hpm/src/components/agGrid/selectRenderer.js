
import Vue from 'vue'
export default Vue.extend({
  data: () => ({
    val: null,
    text: null
  }),
  created () {
    this.setValue(this.params)
  },
  methods: {
    refresh (params) {
      this.params = params
      this.setValue(params)
    },
    setValue (params) {
      console.log(params)
      this.val = params.value
      for (const item of params.colDef.cellEditorParams.values) {
        if (item.id === params.value) {
          this.text = item.name
        }
      }
    },
    setDataValue (params) {
      console.log(params)
    },
    formatValue (params) {
      console.log(params)
    }
  },
  template: `<div><input type="hidden" v-model="val"/> <div>{{text}}</div></div>`
})
