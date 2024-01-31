<template>
  <div class="container">
    <div class="row justify-content-left">
      <Panel title="Tab">
        <textarea id="tab" v-model="tab" class="form-control" required readonly></textarea>
      </Panel>
    </div>
  </div>
</template>

<script>
import Panel from '@/components/Panel'

export default {
  props: ['tab'],
  components: {
    Panel
  }
}
</script>

<style scoped>
textarea {
  height: 600px;
  font-family: monospace;
  border: none;
  border-style: none;
  border-color: transparent;
  overflow: auto;
  padding: 40px;
}
</style>
