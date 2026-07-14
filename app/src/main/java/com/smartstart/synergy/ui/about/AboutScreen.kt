package com.smartstart.synergy.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartstart.synergy.ui.components.SectionScaffold

@Composable
fun AboutScreen(onBack: () -> Unit) {
    SectionScaffold(
        title = "About Developer",
        onBack = onBack
    ) { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Noel Mhone",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = """
                    Noel Mhone is an accomplished educator and chemist from Malawi. He holds a Bachelor of Education Science degree from the University of Malawi where he specialized in chemistry. With a passion for teaching, Noel has taught students for over fourteen years in various subjects and courses including chemistry, physics, science, and mathematics. He has taught at Chibavi Community Day Secondary School, Mzuzu Academy, Marymount, Bandawe Girls, Providence Girls, Matindi Private, St John of God College of Health Sciences and Mzuzu University.

                    In 2022, Noel was recognized for his exceptional teaching skills and was selected as one of the two teachers from Malawi to participate in the prestigious Fulbright Teaching Excellence and Achievement (TEA) program in the United States. He pursued his Fulbright TEA studies at the University of Nevada Reno, where he further enhanced his knowledge and teaching techniques. During his time in the USA, Noel had the opportunity to share his expertise as an assistant teacher at Proctor R Hug High School.

                    His passion for education extended beyond traditional teaching methods, as he taught himself coding and used this knowledge to develop several educational mobile applications. These applications have been widely adopted by students and teachers across Malawi, contributing to enhanced learning experiences. Additionally, Noel designed a tool that numerous schools utilize to generate comprehensive student performance progress reports.

                    His diverse experiences in education, combined with his proficiency in coding and technology, have allowed him to make significant contributions to the educational landscape in his country. His dedication to improving education and his innovative approach to teaching have positively impacted students and teachers throughout Malawi.

                    Presently, Noel Mhone is employed as a chemist at the Ministry of Mining Headquarters in Malawi where he is applying his scientific expertise in a different capacity. His role involves conducting research, analyzing samples, and contributing to the implementation of mining policies in the country.

                    Noel Mhone's journey exemplifies his unwavering commitment to education and innovation. His dedication to teaching, technological proficiency, and contributions to the education sector in Malawi have made him a respected figure in both the academic and scientific communities.
                """.trimIndent(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
