<template>
  <div class="container">
    <div class="row justify-content-left">
      <panel title="Tab">
        <textarea id="tab" v-model="tab" class="form-control" required readonly></textarea>
      </panel>
    </div>
  </div>
</template>

<script>
export default {
  props: ['tab']
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
