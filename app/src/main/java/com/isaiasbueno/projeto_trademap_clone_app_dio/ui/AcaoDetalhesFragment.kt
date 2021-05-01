package com.isaiasbueno.projeto_trademap_clone_app_dio.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.isaiasbueno.projeto_trademap_clone_app_dio.R
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.Acao
import com.isaiasbueno.projeto_trademap_clone_app_dio.extension.formatarMoeda
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_acao_detalhe.*
import org.koin.android.viewmodel.ext.android.viewModel
class AcaoDetalhesFragment : Fragment(R.layout.fragment_acao_detalhe) {

    private val viewModel: AcaoViewModel by viewModel()

    private val arguments by navArgs<AcaoDetalhesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val acao = arguments.acao
        viewModel.getUltimo(acao.codigo).observe(viewLifecycleOwner, {
            textViewCodigo.text = it.codigo
            textViewValor.text = it.valor.formatarMoeda()
        })
        observaAcoes(acao)
    }

    private fun observaAcoes(acao: Acao) {
        viewModel.getTodos(acao.codigo).observe(viewLifecycleOwner, { acoes ->
            val pontos = mutableListOf<Entry>()
            acoes?.forEachIndexed { index, acao ->
                pontos.add(Entry(index.toFloat(), acao.valor.toFloat()))
            }
            criarGrafico(pontos)
        })
    }

    fun criarGrafico(pontos: MutableList<Entry>) {
        val lineDataSet = LineDataSet(pontos, "Ações").apply {
            lineWidth = 1.8f
            valueTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
            fillColor = ContextCompat.getColor(requireContext(), R.color.colorAccent)
            circleColors =
                mutableListOf(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            fillAlpha = 170
            setDrawFilled(true)
            setDrawCircles(true)
        }
        lineChart.apply {
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
            }
            axisLeft.apply {
                setDrawGridLines(false)
            }
            axisRight.isEnabled = false
            axisLeft.textColor = ContextCompat.getColor(context, android.R.color.white)
            xAxis.textColor = ContextCompat.getColor(context, android.R.color.white)
            description.isEnabled = false
            legend.isEnabled = false
            animateY(1000)
            data = LineData(lineDataSet)
        }
    }

}