package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeveloperScreen(
                        profil = sampleDeveloper(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Donnée Profil locale
data class ProfilUi(
    val name: String,
    val title: String,
    val description: String,
    val mail: String,
    val phoneNumber: String,
    val skillsList: List<String>
)

fun sampleDeveloper(): ProfilUi {
    return ProfilUi(
        name = "Paul NOWAK",
        title = "Concepteur Développeur",
        description = "Etudiant en première année de mastère spécialisé dans l'architecture des logiciels.",
        skillsList = listOf("C++", "Python", "TypeScript"),
        mail = "p.nowak@myskolae.fr",
        phoneNumber = "06 86 92 04 17",
    )
}



// L'écran du profil développeur
@Composable
fun DeveloperScreen(profil: ProfilUi, modifier: Modifier = Modifier) {
    DeveloperFile(
        name = profil.name,
        title = profil.title,
        text = profil.description,
        skillsList = profil.skillsList,
        mail = profil.mail,
        isShown = true,
        onPortfolioClick = {
            // Plus tard : afficher le portfolio du développeur
        },
        onFileClick = {
            // Plus tard : ouvrir le détail de la fiche développeur
        },
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun DeveloperFile(
    name: String,
    title: String,
    text: String,
    mail: String,
    skillsList: List<String>,
    isShown: Boolean,
    onPortfolioClick: () -> Unit,
    onFileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .clickable(
                role = Role.Button,
                onClick = onFileClick
            )
            .semantics {
                contentDescription = "Ouvrir le détail de la fiche développeur de $name"
            }
    ) {
        DeveloperFileContent(
            name = name,
            title = title,
            text = text,
            mail = mail,
            skillsList = skillsList,
            isShown = isShown,
            onPortfolioClick = onPortfolioClick,
            modifier = modifier
        )
    }
}

@Composable
fun DeveloperFileContent(
    name: String,
    title: String,
    text: String,
    mail: String,
    skillsList: List<String>,
    isShown: Boolean,
    onPortfolioClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        DeveloperHeading(
            name = name,
            title = title,
            modifier = modifier.fillMaxWidth()
        )
        DeveloperDescription(
            text = text,
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Button(onClick = onPortfolioClick) {
            Text(
                text = if (isShown) {
                    "Cacher le portfolio ▲"
                } else {
                    "Montrer le portfolio ▼"
                },
            )
        }
        if (isShown ) {
            DeveloperPortfolio(
                skillsList = skillsList,
                modifier = modifier
            )
        }
        DeveloperMail(
            mail = mail,
            fontSize = 20,
            modifier = modifier
        )
    }

}

@Composable
fun DeveloperHeading(
    name: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Surface(color = Color.LightGray) {
        Column {
            DeveloperName(
                name = name,
                fontSize = 35,
                modifier = modifier
            )
            DeveloperTitle(
                title = title,
                fontSize = 25,
                modifier = modifier
            )

        }
    }
}

@Composable
fun DeveloperName(
    name: String,
    fontSize: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        fontSize = fontSize.sp,
        modifier = modifier,
        color = Color.Red
    )
}

@Composable
fun DeveloperTitle(
    title: String,
    fontSize: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun DeveloperDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun DeveloperPortfolio(
    skillsList: List<String>,
    modifier: Modifier = Modifier
) {
    Column (
            modifier = modifier.fillMaxWidth(),
        ){
            DeveloperSkillsList(
                skillsList = skillsList
            )
            Spacer(Modifier.height(8.dp))
            ExperienceCard(

                experience = "Développeur Full Stack (Stage)",
                datesExperience = "Novembre 2025 - Mars 2026",
                descriptionExperience = "Réalisation de prototypes d'applications web"
            )

    }

}

@Composable
fun DeveloperSkillsList(
    skillsList: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Mes compétences :",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(skillsList) { skill ->
                DeveloperSkillChip(
                    label = skill,
                    modifier = modifier
                )
            }
        }
    }

}



@Composable
fun ExperienceCard(
    experience: String,
    datesExperience: String,
    descriptionExperience: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = experience,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Dates : $datesExperience",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = descriptionExperience,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun DeveloperSkillChip(
    label : String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        )
    }

}

@Composable
fun ShowSkillsButton(
    isShown: Boolean,
    fontSize: Int,
    onSkillsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onSkillsClick,
        modifier = modifier
    ) {
        Text(
            text = if (isShown) {
                "Cacher▲"
            } else {
                "Montrer▼"
            },
            fontSize = fontSize.sp
        )
    }
}

@Composable
fun DeveloperMail(
    mail : String,
    fontSize: Int,
    modifier: Modifier = Modifier
) {

    Text(
        text = "Mon mail:  $mail",
        fontSize = fontSize.sp,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun DeveloperScreenPreview() {
    ProfilDeveloppeurTheme {
        DeveloperScreen(profil = sampleDeveloper())
    }
}

@Preview(showBackground = true)
@Composable
fun ProductSkillsShownPreview() {
    ProfilDeveloppeurTheme {
        DeveloperFile(
            name = "Paul NOWAK",
            title = "Concepteur Développeur",
            text = "Etudiant en première année de mastère spécialisé dans l'architecture des logiciels.",
            skillsList = listOf("C++", "Python", "TypeScript"),
            mail = "p.nowak@myskolae.fr",
            isShown = true,
            onPortfolioClick = {
                // Plus tard : afficher le portfolio du développeur
            },
            onFileClick = {
                // Plus tard : ouvrir le détail de la fiche développeur
            },
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductSkillsHiddenPreview() {
    ProfilDeveloppeurTheme {
        DeveloperFile(
            name = "Paul NOWAK",
            title = "Développeur Full Stack",
            text = "Etudiant en première année de mastère spécialisé dans l'architecture des logiciels.",
            skillsList = listOf("C++", "React", "HTML"),
            mail = "p.nowak@myskolae.fr",
            isShown = false,
            onPortfolioClick = {
                // Plus tard : afficher le portfolio du développeur
            },
            onFileClick = {
                // Plus tard : ouvrir le détail de la fiche développeur
            },
            modifier = Modifier
                .padding(16.dp)
        )
    }
}