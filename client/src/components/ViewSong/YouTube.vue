<template>
  <div class="container">
    <div class="row justify-content-left" style="height: 350px">
      <panel title="YouTube Video">
        <youtube :video-id="youtubeId" :player-width="500" :player-height="235"></youtube>
      </panel>
    </div>
  </div>
</template>

<script>
export default {
  props: ['youtubeId']
}
</script>
