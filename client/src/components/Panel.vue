<template>
  <div class="card">
    <div class="card-body">
      <h2 class="text-center mb-4">
        {{title}}
        <slot name="button"></slot>
      </h2>
      <slot>
        No slot content defined.
      </slot>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title']
}
</script>
