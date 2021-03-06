package com.ecosnap.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecosnap.R
import kotlinx.android.synthetic.main.fragment_month.view.*

class MonthFragment : Fragment() {
    private lateinit var dataString: String
    private var content = ""

    fun newInstance(dataString: String): MonthFragment {
        val monthFragment= MonthFragment()
        val args = Bundle()
        args.putString("data", dataString)
        monthFragment.arguments = args
        return monthFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataString = arguments?.getSerializable("data") as String
        content = ("<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load(\"current\", {packages:[\"corechart\"]});"
                + "      google.charts.setOnLoadCallback(drawChart);"
                + "      function drawChart() {"
                + "        var data = google.visualization.arrayToDataTable(["
                + "         ['Dates', 'Recyclable', 'Not Recyclable'],"
                +           dataString
                + "        ]);"
                + "        var options = {"
                + "          chartArea: {left: 72, top: 32},"
                + "          forceIFrame: true,"
                + "          colors: ['#00796B', '#a94850'],"
                + "          legend: {position: 'bottom', alignment: 'center'}"
                + "        };"
                + "        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));"
                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"chart_div\" style=\"width: 100%; height: 80%;\" ></div>"
                + "  </body>" + "</html>")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_month, container, false)
        val webview = view.chart_month
        val webSettings = webview.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webview.requestFocusFromTouch()
        webview.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null)
        return view
    }
}
