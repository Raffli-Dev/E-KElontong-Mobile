<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Report extends MY_Controller
{
    public function __construct(){
        parent::__construct();
        $this->load->model('reports_model','reports');
    }
    public function get_profit_loss_reports(){
        echo json_encode($this->reports->get_profit_loss_report_one_year());
    }
}