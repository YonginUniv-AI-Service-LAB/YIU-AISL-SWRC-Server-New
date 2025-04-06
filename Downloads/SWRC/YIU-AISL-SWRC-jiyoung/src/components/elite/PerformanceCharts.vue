<template>
  <div class="chart-container">
    <!-- 지구력 -->
    <div class="chart-box">
      <h2>지구력 변화</h2>
      <Line
        :data="staminaData"
        :options="commonOptions"
      />
    </div>

    <!-- 근력 -->
    <div class="chart-box">
      <h2>근력 변화</h2>
      <Line
        :data="muscleData"
        :options="commonOptions"
      />
    </div>

    <!-- 점프력 -->
    <div class="chart-box">
      <h2>점프력 변화</h2>
      <Line
        :data="jumpData"
        :options="commonOptions"
      />
    </div>

    <!-- 순발력 -->
    <div class="chart-box">
      <h2>순발력 변화</h2>
      <Line
        :data="quicknessData"
        :options="commonOptions"
      />
    </div>
  </div>
</template>

<script>
import { defineComponent, ref, watch } from 'vue';
import { Line } from 'vue-chartjs';
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  Filler
} from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  ChartDataLabels,
  Filler
);

export default defineComponent({
  name: 'PerformanceCharts',
  components: { Line },
  props: {
    externalRecords: {
      type: Array,
      default: () => []
    }
  },
  setup(props) {
    const staminaData = ref({ labels: [], datasets: [] });
    const muscleData = ref({ labels: [], datasets: [] });
    const jumpData = ref({ labels: [], datasets: [] });
    const quicknessData = ref({ labels: [], datasets: [] });

    const gradientBackground = (context) => {
      const chart = context.chart;
      const { ctx, chartArea } = chart;
      if (!chartArea) return null;
      const gradient = ctx.createLinearGradient(0, chartArea.top, 0, chartArea.bottom);
      gradient.addColorStop(0, 'rgba(114,189,214,0.5)');
      gradient.addColorStop(1, 'rgba(114,189,214,0)');
      return gradient;
    };

    const commonOptions = {
      responsive: true,
      maintainAspectRatio: false,
      layout: { padding: { top: 20, bottom: 35 } },
      plugins: {
        tooltip: { enabled: false },
        legend: { display: false },
        datalabels: {
          display: (ctx) => {
            const dataset = ctx.dataset;
            const index = ctx.dataIndex;
            return index === dataset.data.length - 1 || ctx.active;
          },
          color: '#005871',
          align: 'end',
          anchor: 'end',
          backgroundColor: '#fff',
          borderColor: '#D9D9D9',
          borderWidth: 1,
          borderRadius: 4,
          padding: { top: 6, right: 8, bottom: 6, left: 8 },
          offset: 6,
          font: { size: 12 },
          formatter: (value, ctx) => {
            const label = ctx.dataset.label;
            if (label === '지구력') return value + '분';
            if (label === '근력') return value + 'kg';
            if (label === '점프력') return value + 'cm';
            if (label === '순발력') return value + 's';
            return value;
          }
        }
      },
      scales: {
        x: { offset: true, grid: { display: false } },
        y: {
          suggestedMin: 0,
          suggestedMax: 90,
          ticks: { maxTicksLimit: 6 },
          border: { display: false }
        }
      }
    };

    const updateChartData = () => {
      const grouped = {
        지구력: [],
        근력: [],
        점프력: [],
        순발력: []
      };

      (props.externalRecords || []).forEach((item) => {
        const field = item.field;
        const value = parseFloat(item.record);
        if (grouped[field] && !isNaN(value)) {
          grouped[field].push({ date: item.date, value });
        }
      });

      const buildData = (label, items) => ({
        labels: items.map(i => i.date),
        datasets: [{
          label,
          data: items.map(i => i.value),
          borderColor: '#005871',
          borderWidth: 1,
          tension: 0.3,
          fill: true,
          backgroundColor: gradientBackground,
          pointRadius: 3,
          pointBackgroundColor: '#ffffff',
          pointBorderColor: '#005871',
          pointBorderWidth: 2,
          pointHitRadius: 10
        }]
      });

      staminaData.value = buildData('지구력', grouped['지구력']);
      muscleData.value = buildData('근력', grouped['근력']);
      jumpData.value = buildData('점프력', grouped['점프력']);
      quicknessData.value = buildData('순발력', grouped['순발력']);
    };

    watch(() => props.externalRecords, updateChartData, { immediate: true });

    return {
      staminaData,
      muscleData,
      jumpData,
      quicknessData,
      commonOptions
    };
  }
});
</script>

<style scoped>
.chart-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 20px; 
    width: 100%;
    max-width: 1100px;
    height: 100%;
    margin: 0 auto;
}
.chart-box {
    position: relative;
    padding: 16px;
    border-radius: 4px;
    background-color: #FFFFFF;
    height: 315px;
    display: flex; /* 세로 정렬 */
    flex-direction: column;
    justify-content: flex-start;
}
.chart-box h2 {
    font-size: 16px;
    margin-top: 0px;
    margin-bottom: 15px;
    text-align: left;
}
.chart-box canvas {
    width: 100%;
    height: auto;
    flex: 1;
    z-index: 1;
}
</style>