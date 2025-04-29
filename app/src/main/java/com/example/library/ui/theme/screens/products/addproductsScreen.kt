package com.example.library.ui.theme.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button

import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.library.R
import com.example.library.data.ProductViewModel
import com.example.library.navigation.ROUTE_VIEW_PRODUCTS

@Composable
fun AddproductScreen(navController: NavController){
    val imageUri = rememberSaveable(){ mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri:Uri? ->uri?.let{imageUri.value=it} }
    var productname by remember { mutableStateOf(value = "") }
    var productquantity by remember { mutableStateOf(value = "") }
    var productprice by remember { mutableStateOf(value = "") }
    var productbrand by remember { mutableStateOf(value = "") }
    var desc by remember { mutableStateOf(value = "") }
    val productViewModel : ProductViewModel =viewModel()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "ADD NEW PRODUCT",

            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .background(color = Color.Black)

        )
        Card(shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(model = imageUri.value ?:R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
                    .clickable{launcher.launch("image/*")})
        }
        Text(text = "Attach product image")
        OutlinedTextField(value = productname,
            onValueChange = {newProductname->productname=newProductname},
            label = {Text(text = "Product Name")},
            placeholder = {Text(text = "Please enter product name")},
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productquantity,
            onValueChange = {newProductquantity->productquantity=newProductquantity},
            label = {Text(text = "Product Quantity")},
            placeholder = {Text(text ="Please enter product quantity" )},
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productprice,
            onValueChange = {newProductprice->productprice=newProductprice},
            label = {Text(text = "Unit Product Price")},
            placeholder = {Text(text = "Please enter product price")},
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productbrand,
            onValueChange = {newBrand->productbrand=newBrand},
            label = {Text(text = "Product Brand")},
            placeholder = {Text(text = "Please enter  product brand")},
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = desc,
            onValueChange = {newDes->desc=newDes},
            label = {Text(text = "Brief description")},
            placeholder = {Text(text = "Please enter product description")},
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false)
        Spacer(modifier = Modifier.height(10.dp))

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {
                navController.navigate(ROUTE_VIEW_PRODUCTS)
            }) { Text(text = "All Products") }
            Button(onClick = {
                imageUri.value?.let {
                    productViewModel.uploadProductWithImage(it, context, productname, productquantity, productprice,productbrand,desc)
                } ?: Toast.makeText(context, "Please pick an image", Toast.LENGTH_SHORT).show()
            }) { Text(text = "SAVE") }
        }

    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddproductScreenPreview(){
    AddproductScreen(rememberNavController())
}