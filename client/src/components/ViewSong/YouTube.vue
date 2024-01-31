<template>
  <div class="container">
    <div class="row justify-content-left" style="height: 350px">
      <Panel title="YouTube Video">
        <youtube :video-id="youtubeId" :player-width="500" :player-height="235"></youtube>
      </Panel>
    </div>
  </div>
</template>

<script>
import Panel from '@/components/Panel'

export default {
  props: ['youtubeId'],
  components: {
    Panel
  }
}
</script>
