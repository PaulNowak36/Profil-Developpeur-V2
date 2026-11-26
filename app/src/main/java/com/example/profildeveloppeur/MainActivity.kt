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
    val skillsList: List<String>,
    val experiencesList: List<ExperienceUI>
)

data class ExperienceUI(
    val id: Int,
    val title: String,
    val dates: String,
    val description: String,
)

fun sampleDeveloper(): ProfilUi {
    return ProfilUi(
        name = "Paul NOWAK",
        title = "Concepteur Développeur",
        description = "Etudiant en première année de mastère spécialisé dans l'architecture des logiciels.",
        skillsList = listOf("C++", "Python", "TypeScript"),
        experiencesList = sampleExperiences(),
        mail = "p.nowak@myskolae.fr",
        phoneNumber = "06 86 92 04 17",
    )
}

fun sampleDeveloper2(): ProfilUi {
    return ProfilUi(
        name = "Paul NOWAK",
        title = "Développeur Full Stack",
        skillsList = listOf("C++", "React", "HTML"),
        description = "Etudiant en première année de mastère spécialisé dans l'architecture des logiciels.",
        mail = "p.nowak@myskolae.fr",
        experiencesList = sampleExperiences(),
        phoneNumber = "06 86 92 04 17",
    )
}

fun sampleExperience(): ExperienceUI {
    return ExperienceUI(
        id = 1,
        title = "Développeur Full Stack (Stage)",
        dates = "Novembre 2025 - Mars 2026",
        description = "Réalisation de prototypes d'applications web"
    )
}

fun sampleExperiences(): List<ExperienceUI> {
    return listOf(
        sampleExperience(),
        ExperienceUI(
            id = 2,
            title = "Informatique embarqué (Stage)",
            dates = "Mars 2021 - Juin 2021",
            description = "Prototype de porte automatique pour poulailler"
        ),
        ExperienceUI(
            id = 3,
            title = "Installation de logiciel (Stage)",
            dates = "Mars 2021 - Juin 2021",
            description = "Choix et installation du CRM"
        )
    )
}



// L'écran du profil développeur
@Composable
fun DeveloperScreen(profil: ProfilUi, modifier: Modifier = Modifier) {
    DeveloperFile(
        profile = sampleDeveloper(),
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
    profile: ProfilUi,
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
                contentDescription = "Ouvrir le détail de la fiche développeur."
            }
    ) {
        DeveloperFileContent(
            Profile = profile,
            isShown = isShown,
            onPortfolioClick = onPortfolioClick,
            modifier = modifier
        )
    }
}

@Composable
fun DeveloperFileContent(
    Profile: ProfilUi,
    isShown: Boolean,
    onPortfolioClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier.fillMaxSize()
    ) {
        DeveloperHeading(
            name = Profile.name,
            title = Profile.title,
            modifier = modifier.fillMaxWidth()
        )
        DeveloperDescription(
            text = Profile.description,
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
                experienceList = Profile.experiencesList,
                skillsList = Profile.skillsList,
                modifier = modifier
            )
        }
        DeveloperMail(
            mail = Profile.mail,
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
    experienceList: List<ExperienceUI>,
    modifier: Modifier = Modifier
) {
    Column (
            modifier = modifier.fillMaxWidth(),
        ){
            DeveloperSkillsList(
                skillsList = skillsList
            )
            Spacer(Modifier.height(8.dp))
            ExperienceSection(
                experienceList = experienceList
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
fun ExperienceSection(
    experienceList: List<ExperienceUI>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Mes expériences :",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(experienceList) { experience ->
                ExperienceCard(
                    experience = experience,
                    modifier = modifier
                )
            }
        }
    }

}

@Composable
fun ExperienceCard(
    experience: ExperienceUI,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = experience.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Dates : ${experience.dates}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = experience.description,
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
            profile = sampleDeveloper2(),
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
            profile = sampleDeveloper2(),
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