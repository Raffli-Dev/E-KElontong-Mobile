<?php
function NumberToWords($no){ 
    $CI =& get_instance();
  $number_to_words_format = get_site_details()->number_to_words;
  
  if($number_to_words_format=='Indian'){
    return indianCurrency($no);
  }
  else{
//    return foreign_currency($no,strtoupper($CI->session->userdata('CURRENCY_CODE')));
    return defaultCurrency($no);
  }
}

function defaultCurrency($number) 
    {
        if (($number < 0) || ($number > 999999999)) 
        {
            throw new Exception("Jumlahnya di luar jangkauan");
        }
        $giga = floor($number / 1000000);
        // Millions (giga)
        $number -= $giga * 1000000;
        $kilo = floor($number / 1000);
        // Thousands (kilo)
        $number -= $kilo * 1000;
        $hecto = floor($number / 100);
        // Hundreds (hecto)
        $number -= $hecto * 100;
        $deca = floor($number / 10);
        // Tens (deca)
        $n = $number % 10;
        // Ones
        $result = "";
        if ($giga) 
        {
            $result .= defaultCurrency($giga) .  "Juta";
        }
        if ($kilo) 
        {
            $result .= (empty($result) ? "" : " ") .defaultCurrency($kilo) . " Ribu";
        }
        if ($hecto) 
        {
            $result .= (empty($result) ? "" : " ") .defaultCurrency($hecto) . " Ratus";
        }
        $ones = array("", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepululuh", "Sebelas", "Dua Belas", "Tiga Belas", "Empat Belas", "Lima Belas", "Enam Belas", "Tujuh Belas", "Delapan Belas", "Sembilan Belas");
        $tens = array("", "", "Dua Puluh", "Tiga Puluh", "Empat Puluh", "Lima Puluh", "Enam Puluh", "Tuju Puluh", "Delapan Puluh", "Sembilan Puluh");
        if ($deca || $n) {
            if (!empty($result)) 
            {
                $result .= " ";
            }
            if ($deca < 2) 
            {
                $result .= $ones[$deca * 10 + $n];
            } else {
                $result .= $tens[$deca];
                if ($n) 
                {
                    $result .= "-" . $ones[$n];
                }
            }
        }
        if (empty($result)) 
        {
            $result = "Nol";
        }
        return $result;
    }

function indianCurrency($amount){

	$hasPaisa=false;
    $arr=explode(".",$amount);
    $rupees=$arr[0];
    if(isset($arr[1])&&((int)$arr[1])>0){
      if(strlen($arr[1])>2){
        $arr[1]=substr($arr[1],0,2);
      }
      $hasPaisa=true;
      $paisa=$arr[1];
    }

    $w="";
    $crore=(int)($rupees/10000000);
    $rupees=$rupees%10000000;
    $w.=single_word($crore,"Cror ");
    $lakh=(int)($rupees/100000);
    $rupees=$rupees%100000;
    $w.=single_word($lakh,"Lakh ");
    $thousand=(int)($rupees/1000);
    $rupees=$rupees%1000;
    $w.=single_word($thousand,"Thousand  ");
    $hundred=(int)($rupees/100);
    $w.=single_word($hundred,"Hundred ");
    $ten=$rupees%100;
    $w.=single_word($ten,"");
    $w.="Rupees ";

    if($hasPaisa){
      if($paisa[0]=="0"){
        $paisa=(int)$paisa;
      }
      else if(strlen($paisa)==1){
        $paisa=$paisa*10;
      }
      $w.=" and ".single_word($paisa," Paisa");
    }
    return $w." Only";
  }

 function single_word($n,$txt){
    $t="";
    if($n<=19){
      $t=words_array($n);
    }else{
      $a=$n-($n%10);
      $b=$n%10;
      $t=words_array($a)." ".words_array($b);
    }
    if($n==0){
      $txt="";
    }
    return $t." ".$txt;
  }

   function words_array($num){
    $n=[0=>"", 1=>"One", 2=>"Two", 3=>"Three", 4=>"Four", 5=>"Five", 6=>"Six", 7=>"Seven", 8=>"Eight", 9=>"Nine", 10=>"Ten", 11=>"Eleven", 12=>"Twelve", 13=>"Thirteen", 14=>"Fourteen", 15=>"Fifteen", 16=>"Sixteen", 17=>"Seventeen", 18=>"Eighteen", 19=>"Nineteen", 20=>"Twenty", 30=>"Thirty", 40=>"Forty", 50=>"Fifty", 60=>"Sixty", 70=>"Seventy", 80=>"Eighty", 90=>"Ninety", 100=>"Hundred",];
    return $n[$num];
  }


