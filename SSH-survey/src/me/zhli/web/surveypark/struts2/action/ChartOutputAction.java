package me.zhli.web.surveypark.struts2.action;

import java.awt.Font;
import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.statistics.OptionStatisticsModel;
import me.zhli.web.surveypark.model.statistics.QuestionStatisticsModel;
import me.zhli.web.surveypark.service.StatisticsService;

/**
 * 图表输出 action
 */
@Controller
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Page> {

	private static final long serialVersionUID = -4436677661548236550L;

	// qid
	private Integer qid;
	// 图表类型
	private int chartType;
	
	@Resource
	private StatisticsService ss;
	
	public String execute() {
		return SUCCESS;
	}
	
	/**
	 * 输出图表
	 */
/*	public InputStream getIs() {
		try {
			// 使用 就freechart 生成图表，并放在 inputstream 中
			QuestionStatisticsModel qsm = ss.statistics(qid);
			// 构造饼图数据集
			DefaultPieDataset ds = new DefaultPieDataset();
			for(OptionStatisticsModel osm : qsm.getOsm()) {
				ds.setValue(osm.getOptionLabel(), osm.getCount());
			}
			JFreeChart chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), ds, true, false, false);
			
			chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));
			chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("宋体", Font.ITALIC, 15));
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(out, chart, 400, 300);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	/**
	 * 改进，直接用 servletOutputStream省去多余的部分
	 */
	public JFreeChart getChart() {
		try {
			// 使用 就freechart 生成图表，并放在 inputstream 中
			QuestionStatisticsModel qsm = ss.statistics(qid);
			// 构造饼图数据集
			DefaultPieDataset ds = new DefaultPieDataset();
			for(OptionStatisticsModel osm : qsm.getOsm()) {
				ds.setValue(osm.getOptionLabel(), osm.getCount());
			}
			JFreeChart chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), ds, true, false, false);
			
			chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));
			chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("宋体", Font.ITALIC, 15));
			
			return chart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public int getChartType() {
		return chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

	public StatisticsService getSs() {
		return ss;
	}

	public void setSs(StatisticsService ss) {
		this.ss = ss;
	}
}
